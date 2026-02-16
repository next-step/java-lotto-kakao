package lotto;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.pick.AutoLottoNumberGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AutoLottoNumberGeneratorTest {

    @Test
    void success_test() {
        AutoLottoNumberGenerator generator = new AutoLottoNumberGenerator();
        List<LottoNumber> result = generator.generate();

        assertThat(result).hasSize(Lotto.REQUIRED_SIZE);
    }

}