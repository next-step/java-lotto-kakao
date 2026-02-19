package controller;

import domain.lotto.LottoFactory;
import domain.lotto.LottoGroup;
import domain.winning.LottoResult;
import domain.winning.WinningLotto;
import service.LottoService;
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

    public void run_step1() {
        int purchaseAmount = inputView.enterPurchaseAmount();
        LottoService lottoService = new LottoService(new LottoFactory());
        LottoGroup lottoGroup = lottoService.issueAutoLottoGroup(purchaseAmount);
        outputView.printLottoCount(lottoGroup);

        List<Integer> winningNumbers = inputView.enterWinningNumbers();
        int bonus = inputView.enterBonusNumber();
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonus);
        LottoResult lottoResult = lottoService.calculateResult(lottoGroup, winningLotto);
        outputView.printStatistics(lottoResult);
    }

    public void run_step2() {
        // 1. 금액, 수동 로또 번호 입력
        int purchaseAmount = inputView.enterPurchaseAmount();
        List<List<Integer>> manualNumbers = inputView.enterManualLottos(inputView.enterManualPurchaseCount());
        // 2. 로또 발행
        LottoService lottoService = new LottoService(new LottoFactory());
        LottoGroup lottoGroup = lottoService.issueMixedLottoGroup(purchaseAmount, manualNumbers);
        outputView.printLottoCount(lottoGroup);
        WinningLotto winningLotto = new WinningLotto(inputView.enterWinningNumbers(), inputView.enterBonusNumber());
        // 3. 결과 계산 및 출력
        LottoResult lottoResult = lottoService.calculateResult(lottoGroup, winningLotto);
        outputView.printStatistics(lottoResult);
    }
}
