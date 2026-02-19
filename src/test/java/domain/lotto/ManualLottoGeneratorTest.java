package domain.lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ManualLottoGeneratorTest {

    @DisplayName("수동 번호 생성기는 입력 순서를 그대로 반환한다")
    @Test
    void generate_numbers_with_input_order() {
        List<Integer> input = List.of(8, 3, 21, 1, 45, 12);
        ManualLottoGenerator generator = new ManualLottoGenerator(input);

        List<Integer> numbers = generator.generate();

        assertThat(numbers).isEqualTo(input);
    }
}
