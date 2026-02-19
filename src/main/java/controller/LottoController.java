package controller;

import lotto.*;
import money.Money;
import utils.InputParser;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Money money = readPurchaseMoneyUntilValid();
        long totalCount = calculateTotalCount(money);
        int manualCount = readManualCountUntilValid(totalCount);
        List<Lotto> manualLottos = readManualLottosUntilValid(manualCount);
        PurchasePlan purchasePlan = PurchasePlan.from(money, manualCount);
        LottosGenerator lottosGenerator = new CompositeLottosGenerator(List.of(
                new ManualLottosGenerator(manualLottos),
                new AutoLottosGenerator(purchasePlan.getAutoCount(), new LottoGenerator())
        ));
        LottoBundle lottoBundle = lottosGenerator.generate();
        outputView.printPurchasedLottos(purchasePlan, lottoBundle);
        WinLotto win = readWinLotto();
        LottoBundleResult lottoBundleResult = lottoBundle.evaluate(win);
        outputView.printStatistic(lottoBundleResult);
        double profitRate = lottoBundleResult.calculateProfitRate();
        outputView.printProfitRate(profitRate);
    }

    private Money readPurchaseMoneyUntilValid() {
        while (true) {
            try {
                return parseMoneyOrThrow(inputView.readPurchaseMoney());
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private long calculateTotalCount(Money money) {
        return PurchasePlan.from(money, 0).getTotalCount();
    }

    private int readManualCountUntilValid(long totalCount) {
        while (true) {
            try {
                return parseManualCountOrThrow(inputView.readManualCount(), totalCount);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private List<Lotto> readManualLottosUntilValid(int manualCount) {
        while (true) {
            try {
                return toManualLottosOrThrow(inputView.readManualLottoNumbers(manualCount));
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private Money parseMoneyOrThrow(String rawMoney) {
        Money money = Money.won(InputParser.parseMoney(rawMoney));
        PurchasePlan.from(money, 0);
        return money;
    }

    private int parseManualCountOrThrow(String rawManualCount, long totalCount) {
        int manualCount = InputParser.parseManualCount(rawManualCount);
        if (manualCount > totalCount) {
            throw new IllegalArgumentException("수동 구매 수량은 전체 구매 가능 수량을 초과할 수 없습니다.");
        }
        return manualCount;
    }

    private List<Lotto> toManualLottosOrThrow(List<String> rawManualLottos) {
        List<Lotto> manualLottos = new ArrayList<>();
        for (String rawManualLotto : rawManualLottos) {
            manualLottos.add(new Lotto(InputParser.parseLottoFormat(rawManualLotto)));
        }
        return manualLottos;
    }

    private WinLotto readWinLotto() {
        while (true) {
            try {
                String rawLotto = inputView.readWinningNumbers();
                Lotto lotto = new Lotto(InputParser.parseLottoFormat(rawLotto));

                String rawBonus = inputView.readBonusNumber();
                LottoNumber bonus = LottoNumber.from(InputParser.parseBonusNumberFormat(rawBonus));

                return new WinLotto(bonus, lotto);
            } catch (Exception e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}
