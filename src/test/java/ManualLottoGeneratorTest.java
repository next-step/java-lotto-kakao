import domains.Lotto;
import domains.ManualLottoGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ManualLottoGeneratorTest {
    private ManualLottoGenerator generator;

    @BeforeEach
    void setUp() {
        generator = new ManualLottoGenerator();
    }

    @Test
    void 중복되지_않은_로또는_정상적으로_추가된다() {
        generator.addManualNumbers(List.of(1, 2, 3, 4, 5, 6));
        generator.addManualNumbers(List.of(7, 8, 9, 10, 11, 12));

        List<Lotto> lottos = generator.generate();

        assertThat(lottos).hasSize(2);
    }

    @Test
    void 이미_존재하는_로또를_추가하면_예외가_발생한다() {
        generator.addManualNumbers(List.of(1, 2, 3, 4, 5, 6));

        List<Integer> duplicateLottoNumbers = List.of(1, 2, 3, 4, 5, 6);

        assertThatThrownBy(() ->  generator.addManualNumbers(duplicateLottoNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }
}