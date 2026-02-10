package controller;

import lotto.*;
import money.Money;
import utils.InputParser;
import view.InputView;
import view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        LottoBundle lottoBundle = purchaseLottoBundle();
        outputView.printPurchasedLottos(lottoBundle);
        WinLotto win = readWinLotto();
        LottoBundleResult lottoBundleResult = lottoBundle.evaluate(win);
        outputView.printStatistic(lottoBundleResult);
        double profitRate = lottoBundleResult.calculateProfitRate();
        outputView.printProfitRate(profitRate);
    }

    private LottoBundle purchaseLottoBundle() {
        while (true) {
            try {
                String raw = inputView.readPurchaseMoney();
                Money money = Money.won(InputParser.parseMoney(raw));
                return LottoBundle.buy(money);
            } catch (Exception e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private WinLotto readWinLotto() {
        while (true) {
            try {
                String rawLotto = inputView.readWinningNumbers();
                Lotto lotto = new Lotto(InputParser.parseLottoFormat(rawLotto));

                String rawBonus = inputView.readBonusNumber();
                LottoNumber bonus = new LottoNumber(InputParser.parseBonusNumberFormat(rawBonus));

                return new WinLotto(bonus, lotto);
            } catch (Exception e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}
