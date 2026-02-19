package domain.lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumberTest {

    @DisplayName("1 이상 45 이하의 로또 번호는 생성할 수 있다")
    @Test
    void create_valid_lotto_number() {
        LottoNumber minNumber = new LottoNumber(1);
        LottoNumber maxNumber = new LottoNumber(45);

        assertThat(minNumber).isNotNull();
        assertThat(maxNumber).isNotNull();
    }

    @DisplayName("1~45 범위를 벗어난 번호로 생성하면 예외가 발생한다")
    @Test
    void create_invalid_lotto_number() {
        assertThatThrownBy(() -> new LottoNumber(0))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new LottoNumber(46))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("같은 숫자의 로또 번호는 동등하다")
    @Test
    void create_same_lotto_number() {
        LottoNumber first = new LottoNumber(7);
        LottoNumber second = new LottoNumber(7);

        assertThat(first).isEqualTo(second);
        assertThat(first.hashCode()).isEqualTo(second.hashCode());
    }

    @DisplayName("다른 숫자의 로또 번호는 동등하지 않다")
    @Test
    void create_different_lotto_number() {
        LottoNumber first = new LottoNumber(7);
        LottoNumber second = new LottoNumber(8);

        assertThat(first).isNotEqualTo(second);
    }

    @DisplayName("toString은 숫자 문자열을 반환한다")
    @Test
    void to_string_returns_number_string() {
        LottoNumber lottoNumber = new LottoNumber(23);

        assertThat(lottoNumber.toString()).isEqualTo("23");
    }
}
