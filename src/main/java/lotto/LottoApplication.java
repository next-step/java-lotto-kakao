package lotto;

import lotto.domain.Lotto;
import lotto.domain.LottoPlayer;
import lotto.domain.LottoStatus;
import lotto.domain.Money;
import lotto.domain.WinningLotto;
import lotto.domain.service.LottoService;
import lotto.view.input.InputView;
import lotto.view.output.OutputView;

import java.util.List;
import java.util.Map;

public class LottoApplication {

    private final LottoService lottoService;
    private final InputView inputView;
    private final OutputView outputView;

    public LottoApplication(
            final LottoService lottoService,
            final InputView inputView,
            final OutputView outputView
    ) {
        this.lottoService = lottoService;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void play() {
        LottoPlayer player = lottoService.createPlayer();
        WinningLotto winningLotto = createWinningLotto();

        Map<LottoStatus, Integer> statuses = winningLotto.countByStatus(player.getLottos());
        Money profit = Money.won(LottoStatus.totalPrize(statuses));
        double profitRate = profit.rateOf(player.getPrice());

        printResult(statuses, profitRate);
    }

    private void printResult(Map<LottoStatus, Integer> statuses, double profitRate) {
        outputView.printWinningStatistics(statuses, profitRate);
    }

    private WinningLotto createWinningLotto() {
        List<Integer> winningNumbers = readWinningLottoNumbers();
        int bonusNumber = readBonusNumber();
        return new WinningLotto(Lotto.fromIntegers(winningNumbers), bonusNumber);
    }

    private List<Integer> readWinningLottoNumbers() {
        outputView.printMessage("지난 주 당첨 번호를 입력해 주세요.");
        return inputView.inputNumbers(",");
    }

    private int readBonusNumber() {
        outputView.printMessage("보너스 볼을 입력해 주세요.");
        return Integer.parseInt(inputView.input());
    }
}
