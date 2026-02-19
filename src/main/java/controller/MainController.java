package controller;

import model.LottoNumber;
import model.LottoNumbers;
import model.LottoResult;
import model.LottoStatistics;
import model.Lottos;
import view.RankView;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

import static model.Lottos.COST;

public class MainController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        Lottos lottos = getLottos();
        LottoResult lottoResult = getLottoResult();
        LottoStatistics lottoStatistics = new LottoStatistics(lottos.getLottos(), lottoResult);
        printLottoStatistics(lottoStatistics);
    }

    private Lottos getLottos() {
        while (true) {
            try {
                outputView.printInputPriceMessage();
                int price = inputPrice();
                outputView.printInputManualCountMessage();
                int manualCount = inputManualCount(price);
                outputView.printInputManualNumbersMessage();
                List<LottoNumbers> lottoNumbersList = inputLottoNumbersList(manualCount);
                Lottos lottos = new Lottos(price, manualCount, lottoNumbersList);
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
                outputView.printInputMainNumbersMessage();
                LottoNumbers mainNumbers = inputLottoNumbers();
                outputView.printInputBonusBall();
                LottoNumber bonusNumber = inputLottoNumber();
                return new LottoResult(mainNumbers, bonusNumber);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private int inputPrice() {
        while (true) {
            try {
                int price = inputView.getNumber();
                if (price < 0) {
                    throw new IllegalArgumentException("음수로는 구매할 수 없습니다.");
                }
                if (price == 0) {
                    throw new IllegalArgumentException("0원으로는 구매할 수 없습니다.");
                }
                if (price % COST != 0) {
                    throw new IllegalArgumentException(COST + "원 단위로 입력해주세요.");
                }
                return price;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private int inputManualCount(int price) {
        while (true) {
            try {
                int manualCount = inputView.getNumber();
                if (manualCount < 0) {
                    throw new IllegalArgumentException("음수로는 구매할 수 없습니다.");
                }
                if (manualCount * COST > price) {
                    throw new IllegalArgumentException("지불한 금액보다 많이 구매할 수 없습니다.");
                }
                return manualCount;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private List<LottoNumbers> inputLottoNumbersList(int manualCount) {
        while (true) {
            try {
                List<LottoNumbers> lottoNumbersList = new ArrayList<>();
                for (int i = 0; i < manualCount; i++) {
                    lottoNumbersList.add(inputLottoNumbers());
                }
                return lottoNumbersList;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private LottoNumbers inputLottoNumbers() {
        while (true) {
            try {
                List<Integer> numbers = inputView.getLottoNumbers();
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

    private LottoNumber inputLottoNumber() {
        while (true) {
            try {
                int number = inputView.getNumber();
                return new LottoNumber(number);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private void printLottoStatistics(LottoStatistics lottoStatistics) {
        outputView.printLottoStatisticsTitle();
        for (RankView rankView : RankView.values()) {
            outputView.printLottoStatisticsDetail(rankView, lottoStatistics);
        }
        outputView.printLottoProfitRates(lottoStatistics);
    }
}
