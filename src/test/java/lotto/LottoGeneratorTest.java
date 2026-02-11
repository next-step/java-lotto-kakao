package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class LottoGeneratorTest {

    @Test
    @DisplayName("생성된 로또는 중복 없이 6개의 유효한 번호를 가진다.")
    void generateLotto() {
        LottoGenerator lottoGenerator = new LottoGenerator();

        for (int i = 0; i < 10; i++) {
            Lotto lotto = lottoGenerator.generate();
            List<LottoNumber> numbers = lotto.numbers();

            assertThat(numbers).hasSize(6);
            assertThat(numbers)
                    .allSatisfy(number -> assertThat(number.value()).isBetween(1, 45));
        }
    }
}
