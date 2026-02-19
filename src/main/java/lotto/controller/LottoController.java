package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputMessage;
import lotto.view.OutputView;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoStore lottoStore;

    public LottoController(InputView inputView, OutputView outputView, LottoStore lottoStore) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoStore = lottoStore;
    }

    public static void main(String[] args) throws IOException {
        new LottoController(new InputView(), new OutputView(), new LottoStore()).run();
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
        Set<LottoNumber> answers = inputView.readLottoNumbersList();

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
        int count = inputView.readPurchaseAmount() / Lotto.getPrice();

        outputView.write(OutputMessage.INPUT_MANUAL_LOTTO_COUNT);
        int manualCount = inputView.readPurchaseAmount();

        outputView.write(OutputMessage.INPUT_MANUAL_LOTTO_NUMBERS);
        List<Lotto> manualLotto = inputView.readManualLottos(manualCount);

        int autoCount = count - manualCount;

        MyLotto myLotto= lottoStore.buy(manualLotto, autoCount);

        outputView.write(OutputMessage.PURCHASE_COUNT, manualCount, autoCount);
      
        return myLotto;
    }
}
