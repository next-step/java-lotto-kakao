package controller;

import lotto.LottoBundle;
import lotto.LottoBundleResult;
import lotto.LottoShop;
import lotto.WinLotto;
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
        outputView.printPurchasedLottos(lottoBundle);
        WinLotto win = readWinLotto();
        LottoBundleResult lottoBundleResult = lottoBundle.evaluate(win);
        outputView.printStatistic(lottoBundleResult);
        double profitRate = lottoBundleResult.calculateProfitRate(lottoBundlePurchase.paid());
        outputView.printProfitRate(profitRate);
    }

    private LottoBundlePurchase purchaseLottoBundle() {
        while (true) {
            try {
                String raw = inputView.readPurchaseMoney();
                Money money = Money.won(InputParser.parseMoney(raw));
                return LottoShop.purchaseBundle(money);
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
}
