package controller;

import lotto.*;
import money.Money;
import purchase.LottoBundlePurchase;
import utils.InputParser;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        LottoBundlePurchase lottoBundlePurchase = purchaseLottoBundle();
        LottoBundle lottoBundle = lottoBundlePurchase.lottoBundle();
        WinLotto win = readWinLotto();
        LottoBundleResult lottoBundleResult = lottoBundle.evaluate(win);
        outputView.printStatistic(lottoBundleResult);
        double profitRate = lottoBundleResult.calculateProfitRate(lottoBundlePurchase.paid());
        outputView.printProfitRate(profitRate);
    }

    private LottoBundlePurchase purchaseLottoBundle() {
        while (true) {
            try {
                LottoPurchaseRequest request = readPurchaseRequest();
                LottoBundlePurchase purchase = LottoShop.purchaseBundle(request.purchaseAmount(), request.lottoForm());
                outputView.printPurchasedLottos(purchase.lottoBundle(), request.manualCount());
                return purchase;
            } catch (Exception e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private LottoPurchaseRequest readPurchaseRequest() {
        Money money = readValidPurchaseMoney();
        long totalCount = calculatePurchasableCount(money);
        int manualCount = readValidManualCount(totalCount);
        LottoForm lottoForm = readManualLottoForm(manualCount);
        return new LottoPurchaseRequest(money, manualCount, lottoForm);
    }

    private long calculatePurchasableCount(Money money) {
        long totalCount = money.calculatePurchasableCount(LottoShop.PRICE);
        if (totalCount < 1) {
            throw new IllegalArgumentException("구입금액이 부족합니다.");
        }
        return totalCount;
    }

    private int readValidManualCount(long totalCount) {
        while (true) {
            try {
                return readManualCountOnce(totalCount);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private int readManualCountOnce(long totalCount) {
        String raw = inputView.readManualLottoCount();
        int manualCount = InputParser.parseManualLottoCount(raw);
        if (manualCount < 0) {
            throw new IllegalArgumentException("수동 로또 개수는 0 또는 양수여야 합니다.");
        }
        if (manualCount > totalCount) {
            throw new IllegalArgumentException("수동 로또 개수가 구매 가능한 개수를 초과했습니다.");
        }
        return manualCount;
    }

    private Money readValidPurchaseMoney() {
        while (true) {
            try {
                String raw = inputView.readPurchaseMoney();
                return Money.won(InputParser.parseMoney(raw));
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }


    private LottoForm readManualLottoForm(int count) {
        LottoForm lottoForm = new LottoForm();
        if (count == 0) {
            return lottoForm;
        }
        inputView.printManualLottoGuide();
        for (int i = 0; i < count; i++) {
            markManualLottoOnce(lottoForm);
        }
        return lottoForm;
    }

    private void markManualLottoOnce(LottoForm lottoForm) {
        while (true) {
            try {
                String raw = inputView.readManualLottoNumbers();
                lottoForm.mark(InputParser.parseLottoFormat(raw));
                return;
            } catch (Exception e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private WinLotto readWinLotto() {
        while (true) {
            try {
                String rawLotto = inputView.readWinningNumbers();
                List<Integer> lottoNumbers = InputParser.parseLottoFormat(rawLotto);

                String rawBonus = inputView.readBonusNumber();
                int bonus = InputParser.parseBonusNumberFormat(rawBonus);

                return new WinLotto(bonus, lottoNumbers);
            } catch (Exception e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private record LottoPurchaseRequest(Money purchaseAmount, int manualCount, LottoForm lottoForm) {
    }
}
