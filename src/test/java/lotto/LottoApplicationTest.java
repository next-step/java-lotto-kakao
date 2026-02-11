package lotto;

import lotto.domain.Lotto;
import lotto.domain.LottoPlayer;
import lotto.domain.LottoPickStrategy;
import lotto.view.input.InputView;
import lotto.view.output.OutputView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

public class LottoApplicationTest {

    @Test
    @DisplayName("통합 테스트")
    public void success_1() {
        Lotto lotto1 = new Lotto(1, 2, 3, 4, 5, 6);
        Lotto lotto2 = new Lotto(7, 8, 9, 10, 11, 12);
        Lotto lotto3 = new Lotto(13, 14, 15, 16, 17, 18);
        List<Lotto> lottos = List.of(lotto1, lotto2, lotto3);

        Lotto winningLottoNumber = new Lotto(1, 2, 3, 4, 5, 6);

        LottoPlayer lottoPlayer = new LottoPlayer(3000, 3, lottos);
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
        public void printLog(List<Integer> list) {
            String result = list.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", ", "[", "]"));

            output.add(result);
        }

        public List<String> getOutput() {
            return output;
        }
    }
}
