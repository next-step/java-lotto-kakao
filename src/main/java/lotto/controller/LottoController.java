package lotto.controller;

import lotto.domain.AnswerLotto;
import lotto.domain.LottoBalls;
import lotto.domain.LottoResults;
import lotto.domain.LottoTotalResult;
import lotto.domain.MyLotto;
import lotto.domain.PurchaseCount;
import lotto.util.NumberAutoCreator;
import lotto.util.NumberCreator;
import lotto.util.NumberManualCreator;
import lotto.view.InputView;
import lotto.view.OutputMessage;
import lotto.view.OutputView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public static void main(String[] args) throws IOException {
        new LottoController().run();
    }

    public void run() throws IOException {
        MyLotto myLotto = makeMyLotto();

        printMyLotto(myLotto);

        AnswerLotto answerLotto = setAnswer();

        printResult(myLotto, answerLotto);
    }

    private MyLotto makeMyLotto() throws IOException {
        int totalCount = getPurchaseCount();
        int manualCount = getManualCount();

        PurchaseCount purchaseCount = new PurchaseCount(totalCount, manualCount);

        outputView.write(OutputMessage.INPUT_MANUAL_PURCHASE_LOTTO_NUMBER);
        List<LottoBalls> lottos = createManualLottos(purchaseCount.getManualCount());

        lottos.addAll(createAutoLottos(purchaseCount.getAutoCount()));

        outputView.write(OutputMessage.TOTAL_PURCHASE_COUNT, purchaseCount.getManualCount(), purchaseCount.getAutoCount());
        return new MyLotto(lottos);
    }

    private int getPurchaseCount() throws IOException {
        outputView.write(OutputMessage.INPUT_PURCHASE_AMOUNT);

        return inputView.readTotalPurchaseAmount() / LottoBalls.getPrice();
    }

    private int getManualCount() throws IOException {
        outputView.write(OutputMessage.INPUT_MANUAL_PURCHASE_AMOUNT);
        return inputView.readManualCount();
    }

    private List<LottoBalls> createManualLottos(int manualCount) throws IOException {
        List<String> manualInputString = inputView.readManualLottoNumbers(manualCount);

        List<LottoBalls> lottoBallsList = new ArrayList<>();

        for (String manualInput : manualInputString) {
            NumberCreator manualCreator = new NumberManualCreator(manualInput);
            lottoBallsList.add(new LottoBalls(manualCreator.numberCreate()));
        }
        return lottoBallsList;
    }

    private List<LottoBalls> createAutoLottos(int autoCount) {
        NumberCreator autoCreator = new NumberAutoCreator();

        List<LottoBalls> autoLottos = new ArrayList<>();

        for (int i = 0; i < autoCount; i++) {
            autoLottos.add(new LottoBalls(autoCreator.numberCreate()));
        }

        return autoLottos;
    }

    private void printMyLotto(MyLotto myLotto) throws IOException {
        String myLottoString = myLotto.getMyLottoStringType();
        outputView.write(OutputMessage.LOTTO_LIST, myLottoString);
    }

    private AnswerLotto setAnswer() throws IOException {
        outputView.write(OutputMessage.INPUT_WINNING_NUMBERS);
        Set<Integer> answers = inputView.readWinningNumbers();

        outputView.write(OutputMessage.INPUT_BONUS_NUMBER);
        int bonusNum = inputView.readBonusNumber();

        return new AnswerLotto(answers, bonusNum);
    }

    private void printResult(MyLotto myLotto, AnswerLotto answerLotto) throws IOException {
        LottoResults lottoResults = new LottoResults(myLotto, answerLotto);

        LottoTotalResult lottoTotalResult = new LottoTotalResult(lottoResults);
        outputView.write(OutputMessage.LOTTO_STATISTICS);
        outputView.write(lottoTotalResult.getTotalResultString());
        outputView.write(OutputMessage.LOTTO_PROFIT, lottoTotalResult.getProfit());
    }
}
