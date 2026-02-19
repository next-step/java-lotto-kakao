package lotto.generator;

import lotto.domain.Lotto;
import lotto.view.InputView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

class ManualLottoGeneratorTest {

    @Test
    @DisplayName("수동 로또를 지정된 개수만큼 생성한다")
    void success_generateManualLottos() {
        // given
        MockInputView inputView = new MockInputView(List.of(
                "1,2,3,4,5,6",
                "7,8,9,10,11,12",
                "13,14,15,16,17,18"
        ));
        ManualLottoGenerator generator = new ManualLottoGenerator(inputView);
        int count = 3;

        // when
        List<Lotto> lottos = generator.generate(count);

        // then
        assertThat(lottos).hasSize(3);
        assertThat(lottos.get(0).getNumbers()).extracting("number")
                .containsExactly(1, 2, 3, 4, 5, 6);
        assertThat(lottos.get(1).getNumbers()).extracting("number")
                .containsExactly(7, 8, 9, 10, 11, 12);
        assertThat(lottos.get(2).getNumbers()).extracting("number")
                .containsExactly(13, 14, 15, 16, 17, 18);
    }

    static class MockInputView implements InputView {
        private Queue<String> queue;

        public MockInputView(List<String> inputs) {
            this.queue = new LinkedList<>(inputs);
        }

        @Override
        public String inputManualLottoCount() {
            return queue.poll();
        }

        @Override
        public String inputPrice() {
            return queue.poll();
        }

        @Override
        public String inputManualLotto() {
            return queue.poll();
        }

        @Override
        public String inputWinningLotto() {
            return queue.poll();
        }

        @Override
        public String inputBonusNumber() {
            return queue.poll();
        }
    }
}
