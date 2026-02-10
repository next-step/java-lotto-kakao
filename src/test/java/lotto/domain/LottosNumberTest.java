package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class LottosNumberTest {

    @Test
    @DisplayName("[성공] 정상적인 로또 넘버를 넘겨준 경우")
    void success_lotto_number() {
        LottoNumber number = new LottoNumber(12);

        int value = number.getValue();
        assertThat(value).isEqualTo(12);
    }

    @Test
    @DisplayName("[예외] 1~45 범위를 벗어난 경우")
    void fail_lotto_range() {
        assertThatThrownBy(() -> {
                    LottoNumber number = new LottoNumber(46); // 범위를 벗어나는 번호 책정
                })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LottoNumber.RANGE_FAIL_MSG);
    }
}
