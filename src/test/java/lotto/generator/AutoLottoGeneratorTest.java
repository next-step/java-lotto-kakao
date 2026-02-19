package lotto.generator;

import lotto.domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AutoLottoGeneratorTest {

    @Test
    @DisplayName("자동 로또를 지정된 개수만큼 생성한다")
    void success_generateAutoLottos() {
        // given
        NumberGenerator numberGenerator = new RandomNumberGenerator();
        AutoLottoGenerator generator = new AutoLottoGenerator(numberGenerator);
        int count = 3;

        // when
        List<Lotto> lottos = generator.generate(count);

        // then
        assertThat(lottos).hasSize(3);
        assertThat(lottos.getFirst().getNumbers()).hasSize(6);
    }

    @Test
    @DisplayName("0개 요청시 빈 리스트를 반환한다")
    void success_generateZeroLottos() {
        // given
        NumberGenerator numberGenerator = new RandomNumberGenerator();
        AutoLottoGenerator generator = new AutoLottoGenerator(numberGenerator);
        int count = 0;

        // when
        List<Lotto> lottos = generator.generate(count);

        // then
        assertThat(lottos).isEmpty();
    }

    @Test
    @DisplayName("NumberGenerator를 통해 로또 번호가 생성된다")
    void success_useInjectedNumberGenerator() {
        // given
        NumberGenerator fixedNumberGenerator = () -> List.of(7, 14, 21, 28, 35, 42);
        AutoLottoGenerator generator = new AutoLottoGenerator(fixedNumberGenerator);

        // when
        List<Lotto> lottos = generator.generate(1);

        // then
        assertThat(lottos).hasSize(1);
        assertThat(lottos.getFirst().getNumbers()).extracting("number")
                .containsExactly(7, 14, 21, 28, 35, 42);
    }
}
