package lotto;

import lotto.domain.*;
import lotto.generator.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.Arrays;
import java.util.List;

public class LottoController {

    private final NumberGenerator numberGenerator;
    private final CompositeLottoGenerator lottoGenerator;
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(
            NumberGenerator numberGenerator,
            CompositeLottoGenerator lottoGenerator,
            InputView inputView,
            OutputView outputView
    ) {
        this.numberGenerator = numberGenerator;
        this.lottoGenerator = lottoGenerator;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public LottoResult play() {
        User user = makeUserInfo();
        printUserLotto(user);
        WinningLotto winningLotto = makeWinningLotto();
        LottoResult result = winningLotto.calculate(user.getLottos(), user.getPrice());
        printResult(result);

        return result;
    }

    private User makeUserInfo() {
        outputView.printPriceMessage();
        Price price = new Price(Integer.parseInt(inputView.inputPrice()));
        int totalCount = price.getLottoCount();
        outputView.printManualLottoCount();
        int manualCount = Integer.parseInt(inputView.inputManualLottoCount());
        List<Lotto> userLottos = makeUserLotto(totalCount, manualCount);
        User user = new User(price, userLottos, manualCount);
        outputView.printLottoCountMessage(user.getManualLottoCount(), user.getAutoLottoCount());
        return user;
    }

    private List<Lotto> makeUserLotto(int totalCount, int manualCount) {
        outputView.printManualLottoInputMessage();
        lottoGenerator.clear();
        lottoGenerator.add(new ManualLottoGenerator(inputView), manualCount);
        lottoGenerator.add(new AutoLottoGenerator(numberGenerator), totalCount - manualCount);
        return lottoGenerator.generate(totalCount);
    }

    private void printUserLotto(User user) {
        for (Lotto lotto : user.getLottos()) {
            outputView.printLog(lotto.getNumbers());
        }
    }

    private WinningLotto makeWinningLotto() {
        String[] winningLottoArray = makeWinningLottoNumbers();
        List<Integer> winningLottoList = Arrays.stream(winningLottoArray)
                .map(Integer::parseInt)
                .toList();

        int bonusNumber = makeBonusNumber();
        return new WinningLotto(new Lotto(winningLottoList), LottoNumber.of(bonusNumber));
    }

    private String[] makeWinningLottoNumbers() {
        outputView.printWinningLottoMessage();
        String winningLottoStr = inputView.inputWinningLotto();
        return winningLottoStr.split(",");
    }

    private int makeBonusNumber() {
        outputView.printBonusNumberMessage();
        return Integer.parseInt(inputView.inputBonusNumber());
    }

    private void printResult(LottoResult result) {
        outputView.printStatistics(result.getStatuses());
        outputView.printProfitRate(result.getProfitRate());
    }

}
