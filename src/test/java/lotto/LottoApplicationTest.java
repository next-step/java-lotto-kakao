package lotto;

import lotto.domain.Lotto;
import lotto.domain.LottoPickStrategy;
import lotto.domain.LottoPlayer;
import lotto.domain.LottoStatus;
import lotto.domain.Lottos;
import lotto.view.input.InputView;
import lotto.view.output.OutputView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import static lotto.domain.LottoStatus.FIVE_CORRECT;
import static lotto.domain.LottoStatus.FIVE_CORRECT_BONUS;
import static lotto.domain.LottoStatus.FOUR_CORRECT;
import static lotto.domain.LottoStatus.SIX_CORRECT;
import static lotto.domain.LottoStatus.THREE_CORRECT;
import static org.assertj.core.api.Assertions.assertThat;

public class LottoApplicationTest {

    @Test
    @DisplayName("통합 테스트")
    public void success_1() {
        Lotto lotto1 = new Lotto(1, 2, 3, 4, 5, 6);
        Lotto lotto2 = new Lotto(7, 8, 9, 10, 11, 12);
        Lotto lotto3 = new Lotto(13, 14, 15, 16, 17, 18);
        List<Lotto> lottos = List.of(lotto1, lotto2, lotto3);

        Lotto winningLottoNumber = new Lotto(1, 2, 3, 4, 5, 6);

        LottoPlayer lottoPlayer = new LottoPlayer(3000, new Lottos(lottos));
        LottoPickStrategy numberGenerator = new FixedNumberGenerator();
        MockInputView inputView = new MockInputView(List.of(
                "3000",
                "1,2,3,4,5,6",
                "7"
        ));
        MockOutputView outputView = new MockOutputView();

        LottoApplication application = new LottoApplication(
                numberGenerator,
                inputView,
                outputView
        );

        application.play();

        assertThat(outputView.getOutput()).containsExactly(
                "구입금액을 입력해 주세요.",
                "3개를 구매했습니다.",
                "[1, 2, 3, 4, 5, 6]",
                "[1, 2, 3, 4, 5, 6]",
                "[1, 2, 3, 4, 5, 6]",
                "지난 주 당첨 번호를 입력해 주세요.",
                "보너스 볼을 입력해 주세요.",
                "당첨 통계",
                "---------",
                "3개 일치 (5000원) - 0개",
                "4개 일치 (50000원) - 0개",
                "5개 일치 (1500000원) - 0개",
                "5개 일치, 보너스 볼 일치 (30000000원) - 0개",
                "6개 일치 (2000000000원) - 3개",
                "총 수익률은 2000000.00입니다."
        );
    }

    static class FixedNumberGenerator implements LottoPickStrategy {

        @Override
        public List<Integer> generate() {
            return List.of(1, 2, 3, 4, 5, 6);
        }
    }

    static class MockInputView implements InputView {

        private Queue<String> queue;

        public MockInputView(List<String> inputs) {
            this.queue = new LinkedList<>(inputs);
        }

        @Override
        public String input() {
            return queue.poll();
        }

        @Override
        public int inputNumber() {
            return Integer.parseInt(queue.poll());
        }
    }

    static class MockOutputView implements OutputView {

        public List<String> output;

        public MockOutputView() {
            this.output = new ArrayList<>();
        }

        @Override
        public void printMessage(String message) {
            output.add(message);
        }

        @Override
        public void printLottos(List<Lotto> lottos) {
            for (Lotto lotto : lottos) {
                output.add(lotto.toString());
            }
        }

        public void printWinningStatistics(
                final Map<LottoStatus, Integer> statuses,
                double profitRate
        ) {
            printMessage("당첨 통계");
            printMessage("---------");

            printStatusLine(THREE_CORRECT, "3개 일치", statuses);
            printStatusLine(FOUR_CORRECT,  "4개 일치", statuses);
            printStatusLine(FIVE_CORRECT,  "5개 일치", statuses);
            printStatusLine(FIVE_CORRECT_BONUS, "5개 일치, 보너스 볼 일치", statuses);
            printStatusLine(SIX_CORRECT,   "6개 일치", statuses);

            printMessage("총 수익률은 " + String.format("%.2f입니다.", profitRate));
        }

        private void printStatusLine(LottoStatus status, String label, Map<LottoStatus, Integer> statuses) {
            int count = statuses.getOrDefault(status, 0);
            printMessage(label + " (" + status.getPrice() + "원) - " + count + "개");
        }

        public List<String> getOutput() {
            return output;
        }
    }
}
