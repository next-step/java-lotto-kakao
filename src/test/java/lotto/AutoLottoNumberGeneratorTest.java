package lotto;

import lotto.domain.pick.AutoLottoNumberGenerator;
import lotto.domain.pick.LottoPickStrategy;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AutoLottoNumberGeneratorTest {

    @Test
    void success_test() {
        AutoLottoNumberGenerator generator = new AutoLottoNumberGenerator();
        List<Integer> result = generator.generate();

        assertThat(result).hasSize(LottoPickStrategy.LOTTO_SIZE);
    }

}