package domain.lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoLottoGeneratorTest {

    @DisplayName("자동 번호 생성기는 6개의 번호를 생성한다")
    @Test
    void generate_six_numbers() {
        AutoLottoGenerator generator = new AutoLottoGenerator(new Random(1));

        List<Integer> numbers = generator.generate();

        assertThat(numbers).hasSize(Lotto.LOTTO_SIZE);
    }

    @DisplayName("자동 번호 생성기는 범위 내 중복 없는 번호를 오름차순으로 생성한다")
    @Test
    void generate_sorted_unique_numbers_in_range() {
        AutoLottoGenerator generator = new AutoLottoGenerator(new Random(2));

        List<Integer> numbers = generator.generate();

        assertThat(numbers)
                .allMatch(number -> number >= LottoNumber.MIN_NUMBER && number <= LottoNumber.MAX_NUMBER)
                .isSorted();
        assertThat(new HashSet<>(numbers)).hasSize(Lotto.LOTTO_SIZE);
    }
}
