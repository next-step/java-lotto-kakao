package lotto.generator;

import lotto.domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CompositeLottoGeneratorTest {

    @Test
    @DisplayName("여러 Generator를 조합하여 로또를 생성한다")
    void success_compositeGenerators() {
        // given
        CompositeLottoGenerator composite = new CompositeLottoGenerator();

        FixedLottoGenerator generator1 = new FixedLottoGenerator(List.of(1, 2, 3, 4, 5, 6));
        FixedLottoGenerator generator2 = new FixedLottoGenerator(List.of(7, 8, 9, 10, 11, 12));

        composite.add(generator1, 1);
        composite.add(generator2, 2);

        // when
        List<Lotto> lottos = composite.generate(0);

        // then
        assertThat(lottos).hasSize(3);
        assertThat(lottos.get(0).getNumbers()).extracting("number")
                .containsExactly(1, 2, 3, 4, 5, 6);
        assertThat(lottos.get(1).getNumbers()).extracting("number")
                .containsExactly(7, 8, 9, 10, 11, 12);
        assertThat(lottos.get(2).getNumbers()).extracting("number")
                .containsExactly(7, 8, 9, 10, 11, 12);
    }

    // 테스트용 고정 로또 생성기
    static class FixedLottoGenerator implements LottoGenerator {
        private final List<Integer> numbers;

        public FixedLottoGenerator(List<Integer> numbers) {
            this.numbers = numbers;
        }

        @Override
        public List<Lotto> generate(int count) {
            List<Lotto> lottos = new java.util.ArrayList<>();
            for (int i = 0; i < count; i++) {
                lottos.add(new Lotto(numbers));
            }
            return lottos;
        }
    }
}
