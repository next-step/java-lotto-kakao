package domain.lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {

    @DisplayName("로또 번호 6개로 로또를 생성할 수 있다")
    @Test
    void create_lotto_with_six_numbers() {
        Lotto lotto = new Lotto(createNumbers(1, 2, 3, 4, 5, 6));

        assertThat(lotto.getNumbers()).hasSize(6);
    }

    @DisplayName("로또 번호가 6개가 아니면 예외가 발생한다")
    @Test
    void throw_exception_when_lotto_size_is_not_six() {
        assertThatThrownBy(() -> new Lotto(createNumbers(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Lotto(createNumbers(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("중복된 로또 번호가 있으면 예외가 발생한다")
    @Test
    void throw_exception_when_lotto_has_duplicate_numbers() {
        assertThatThrownBy(() -> new Lotto(createNumbers(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("contains는 해당 번호 포함 여부를 반환한다")
    @Test
    void contains_returns_whether_number_exists() {
        Lotto lotto = new Lotto(createNumbers(1, 2, 3, 4, 5, 6));

        assertThat(lotto.contains(new LottoNumber(3))).isTrue();
        assertThat(lotto.contains(new LottoNumber(7))).isFalse();
    }

    @DisplayName("countMatch는 두 로또 간 일치하는 번호 개수를 반환한다")
    @Test
    void count_match_returns_match_count() {
        Lotto first = new Lotto(createNumbers(1, 2, 3, 4, 5, 6));
        Lotto second = new Lotto(createNumbers(1, 2, 3, 40, 41, 42));

        assertThat(first.countMatch(second)).isEqualTo(3);
    }

    @DisplayName("toString은 로또 번호 목록 문자열을 반환한다")
    @Test
    void to_string_returns_numbers_string() {
        Lotto lotto = new Lotto(createNumbers(1, 2, 3, 4, 5, 6));

        assertThat(lotto.toString()).isEqualTo("[1, 2, 3, 4, 5, 6]");
    }

    private List<LottoNumber> createNumbers(int... numbers) {
        return java.util.Arrays.stream(numbers)
                .mapToObj(LottoNumber::new)
                .toList();
    }
}
