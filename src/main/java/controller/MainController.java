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
        LottoNumbers mainNumbers = inputMainNumbers();
        LottoNumber bonusNumber = inputBonusNumber();
        LottoResult lottoResult = new LottoResult(mainNumbers, bonusNumber);
        LottoStatistics lottoStatistics = new LottoStatistics(lottos.getLottos(), lottoResult);
        printLottoStatistics(lottoStatistics);
    }

    private Lottos issueLottos() {
        Lottos lottos = null;
        try {
            outputView.printInputPriceMessage();
            int price = inputView.getNumber();
            lottos = new Lottos(price);
            outputView.printLottos(lottos);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);
            issueLottos();
        }
        return lottos;
    }

    private LottoNumbers inputMainNumbers() {
        LottoNumbers mainNumbers = null;
        try {
            outputView.printInputMainNumbersMessage();
            List<Integer> numbers = inputView.getMainNumbers();
            List<LottoNumber> lottoNumbers = new ArrayList<>();
            for (Integer number : numbers) {
                lottoNumbers.add(new LottoNumber(number));
            }
            mainNumbers = new LottoNumbers(lottoNumbers);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);
            inputMainNumbers();
        }
        return mainNumbers;
    }

    private LottoNumber inputBonusNumber() {
        LottoNumber bonusNumber = null;
        try {
            outputView.printInputBonusBall();
            int number = inputView.getNumber();
            bonusNumber = new LottoNumber(number);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);
            inputBonusNumber();
        }
        return bonusNumber;
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
