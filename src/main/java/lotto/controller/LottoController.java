package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputMessage;
import lotto.view.OutputView;

import java.io.IOException;
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

        LottoResults answer = new LottoResults(myLotto, answerLotto);

        LottoTotalResult answer2 = new LottoTotalResult(answer);
        outputView.write(OutputMessage.LOTTO_STATISTICS);
        outputView.write(answer2.getTotalResultString());
        outputView.write(OutputMessage.LOTTO_PROFIT, answer2.getProfit());
    }

    private AnswerLotto setAnswer() throws IOException {
        outputView.write(OutputMessage.INPUT_WINNING_NUMBERS);
        Set<Integer> answers = inputView.readWinningNumbers();

        outputView.write(OutputMessage.INPUT_BONUS_NUMBER);
        int bonusNum = inputView.readBonusNumber();

        return new AnswerLotto(answers, bonusNum);
    }

    private void printMyLotto(MyLotto myLotto) throws IOException {
        String myLottoString = myLotto.getMyLottoStringType();
        outputView.write(OutputMessage.LOTTO_LIST, myLottoString);
    }

    private MyLotto makeMyLotto() throws IOException {
        outputView.write(OutputMessage.INPUT_PURCHASE_AMOUNT);

        int totalCount = inputView.readTotalPurchaseAmount() / LottoBalls.getPrice();

        outputView.write(OutputMessage.INPUT_MANUAL_PURCHASE_AMOUNT);
        int manualCount = inputView.readManualCount();
        int autoCount = totalCount - manualCount;

        outputView.write(OutputMessage.INPUT_MANUAL_PURCHASE_LOTTO_NUMBER);
        MyLotto myLotto = new MyLotto(autoCount, manualCount);
        outputView.write(OutputMessage.TOTAL_PURCHASE_COUNT, manualCount, autoCount);
        return myLotto;
    }
}
