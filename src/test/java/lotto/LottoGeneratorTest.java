package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class LottoGeneratorTest {
    @Test
    void 숫자_6개_생성_테스트() {
        Lotto numbers = LottoGenerator.generateLotto();
        Assertions.assertThat(numbers.toLottoNumbersSize())
                .isEqualTo(6);
    }
}
