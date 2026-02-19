package domain.lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoFactoryTest {

    @DisplayName("자동 로또 하나를 생성하면 6개의 번호가 포함된 로또가 생성된다")
    @Test
    void create_auto_lotto_sorted_numbers() {
        LottoFactory lottoFactory = new LottoFactory();
        LottoGenerator generator = new AutoLottoGenerator(new Random(1));

        Lotto lotto = lottoFactory.create(generator);
        List<Integer> numbers = extractNumbers(lotto);

        assertThat(numbers).hasSize(6);
    }

    @DisplayName("동일한 시드의 Random을 주면 자동 로또 결과가 동일하다")
    @Test
    void create_auto_lotto_is_deterministic_with_seeded_random() {
        LottoFactory firstFactory = new LottoFactory();
        LottoFactory secondFactory = new LottoFactory();

        Lotto first = firstFactory.create(new AutoLottoGenerator(new Random(10)));
        Lotto second = secondFactory.create(new AutoLottoGenerator(new Random(10)));

        assertThat(first.toString()).isEqualTo(second.toString());
    }

    @DisplayName("수동 로또는 입력 번호 순서를 그대로 사용한다")
    @Test
    void create_manual_lotto_with_input_order() {
        LottoFactory lottoFactory = new LottoFactory();
        List<Integer> input = List.of(8, 3, 21, 1, 45, 12);

        Lotto lotto = lottoFactory.create(new ManualLottoGenerator(input));

        assertThat(extractNumbers(lotto)).isEqualTo(input);
    }

    private List<Integer> extractNumbers(Lotto lotto) {
        return lotto.getNumbers().stream()
                .map(Object::toString)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
