package lotto;

import lotto.domain.*;
import lotto.view.input.InputView;
import lotto.view.output.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static lotto.domain.LottoStatus.*;

public class LottoApplication {

    private final LottoPickStrategy randomNumberGenerator;
    private final InputView inputView;
    private final OutputView outputView;

    public LottoApplication(
            LottoPickStrategy randomNumberGenerator,
            InputView inputView,
            OutputView outputView
    ) {
        this.randomNumberGenerator = randomNumberGenerator;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void play() {
        LottoPlayer player = createPlayer();
        WinningLotto winningLotto = createWinningLotto();

        Map<LottoStatus, Integer> statuses = winningLotto.countByStatus(player.getLottos());
        long profit = LottoStatus.totalPrize(statuses);
        double profitRate = (double) profit / player.getPrice();

        printResult(statuses, profitRate);
    }

    private void printResult(
            Map<LottoStatus, Integer> statuses,
            double profitRate
    ) {
        outputView.printMessage("당첨 통계");
        outputView.printMessage("---------");
        outputView.printMessage("3개 일치 (" + THREE_CORRECT.getPrice() + "원) - " + statuses.getOrDefault(THREE_CORRECT, 0) + "개");
        outputView.printMessage("4개 일치 (" + FOUR_CORRECT.getPrice() + "원) - " + statuses.getOrDefault(FOUR_CORRECT, 0) + "개");
        outputView.printMessage("5개 일치 (" + FIVE_CORRECT.getPrice() + "원) - " + statuses.getOrDefault(FIVE_CORRECT, 0) + "개");
        outputView.printMessage("5개 일치, 보너스 볼 일치 (" + FIVE_CORRECT_BONUS.getPrice() + "원) - " + statuses.getOrDefault(FIVE_CORRECT_BONUS, 0) + "개");
        outputView.printMessage("6개 일치 (" + SIX_CORRECT.getPrice() + "원) - " + statuses.getOrDefault(SIX_CORRECT, 0) + "개");

        outputView.printMessage("총 수익률은 " + String.format("%.2f입니다.", profitRate));
    }

    private WinningLotto createWinningLotto() {
        String[] winningLottoArray = makeWinningLottoNumbers();

        List<Integer> winningLottoList = Arrays.stream(winningLottoArray)
                .map(Integer::parseInt)
                .toList();

        int bonusNumber = readBonusNumber();
        return new WinningLotto(new Lotto(winningLottoList), bonusNumber);
    }

    private int readBonusNumber() {
        outputView.printMessage("보너스 볼을 입력해 주세요.");
        return Integer.parseInt(inputView.input());
    }

    private String[] makeWinningLottoNumbers() {
        outputView.printMessage("지난 주 당첨 번호를 입력해 주세요.");
        String winningLottoStr = inputView.input();
        return winningLottoStr.split(",");
    }

    private LottoPlayer createPlayer() {
        outputView.printMessage("구입금액을 입력해 주세요.");
        int price = inputView.inputNumber();
        int lottoCount = price / 1000;
        outputView.printMessage(lottoCount + "개를 구매했습니다.");

        List<Lotto> lottos = buyLottos(lottoCount);
        printPurchasedLottos(lottos);

        return new LottoPlayer(price, lottoCount, lottos);
    }

    private List<Lotto> buyLottos(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto(randomNumberGenerator.generate()));
        }
        return lottos;
    }

    private void printPurchasedLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            outputView.printLog(lotto.getNumbers());
        }
    }

}
