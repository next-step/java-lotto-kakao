package controller;

import java.util.ArrayList;
import java.util.List;

import model.*;
import view.*;

public class MainController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        Lottos lottos = issueLottos();
        LottoResult lottoResult = getLottoResult();
        LottoStatistics lottoStatistics = new LottoStatistics(lottos.getLottos(), lottoResult);
        printLottoStatistics(lottoStatistics);
    }

    private Lottos issueLottos() {
        while (true) {
            try {
                outputView.printInputPriceMessage();
                int price = inputView.getNumber();
                Lottos lottos = new Lottos(price);
                outputView.printLottos(lottos);
                return lottos;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private LottoResult getLottoResult() {
        while (true) {
            try {
                LottoNumbers mainNumbers = inputMainNumbers();
                LottoNumber bonusNumber = inputBonusNumber();
                return new LottoResult(mainNumbers, bonusNumber);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private LottoNumbers inputMainNumbers() {
        while (true) {
            try {
                outputView.printInputMainNumbersMessage();
                List<Integer> numbers = inputView.getMainNumbers();
                List<LottoNumber> lottoNumbers = new ArrayList<>();
                for (Integer number : numbers) {
                    lottoNumbers.add(new LottoNumber(number));
                }
                return new LottoNumbers(lottoNumbers);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private LottoNumber inputBonusNumber() {
        while (true) {
            try {
                outputView.printInputBonusBall();
                int number = inputView.getNumber();
                return new LottoNumber(number);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private void printLottoStatistics(LottoStatistics lottoStatistics) {
        outputView.printLottoStatisticsTitle();
        List<Rank> ranks = List.of(Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.SECOND, Rank.FIRST);
        for (Rank rank : ranks) {
            outputView.printLottoStatisticsDetail(rank, lottoStatistics.getLevelCount(rank));
        }
        outputView.printLottoProfitRates(lottoStatistics.getProfitRates());
    }
}
