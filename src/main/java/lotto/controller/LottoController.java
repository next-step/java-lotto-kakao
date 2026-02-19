package lotto.controller;

import lotto.domain.*;
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

    private void printResult(MyLotto myLotto, AnswerLotto answerLotto) throws IOException {
        LottoResults lottoResults = new LottoResults(myLotto, answerLotto);

        LottoTotalResult lottoTotalResult = new LottoTotalResult(lottoResults);
        outputView.write(OutputMessage.LOTTO_STATISTICS);
        outputView.write(lottoTotalResult.getTotalResultString());
        outputView.write(OutputMessage.LOTTO_PROFIT, lottoTotalResult.getProfit());
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
        List<String> manualInputString = inputView.readManualLottoNumbers(manualCount);

        List<LottoBalls> lottoBallsList = new ArrayList<>();

        for (String manualInput : manualInputString) {
            NumberCreator manualCreator = new NumberManualCreator(manualInput);
            lottoBallsList.add(new LottoBalls(manualCreator.numberCreate()));
        }

        NumberCreator autoCreator = new NumberAutoCreator();
        for (int i = 0; i < autoCount; i++) {
            lottoBallsList.add(new LottoBalls(autoCreator.numberCreate()));
        }

        outputView.write(OutputMessage.TOTAL_PURCHASE_COUNT, manualCount, autoCount);
        return new MyLotto(lottoBallsList);
    }
}
