package lotto.domain;

import lotto.domain.pick.AutoLottoNumberGenerator;
import lotto.domain.pick.LottoPickStrategy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RandomNumberTest {

    @Test
    @DisplayName("성공 케이스")
    void success() {
        LottoPickStrategy module = new AutoLottoNumberGenerator();
        List<Integer> numbers = module.generate();
        Assertions.assertThat(numbers).hasSize(6);
    }
}
