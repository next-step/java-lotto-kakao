package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class LottoNumberTest {

    @Test
    public void 공은_1이상_45이하의_정수여야_한다() {
        LottoNumber lottoNumber = new LottoNumber(1);
        Assertions.assertThat(lottoNumber).isInstanceOf(LottoNumber.class);
    }

    @Test
    public void 공은_1미만일_경우_예외를_던진다() {
        Assertions.assertThatThrownBy(() -> new LottoNumber(0)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void 공은_45초과일_경우_예외를_던진다() {
        Assertions.assertThatThrownBy(() -> new LottoNumber(46)).isInstanceOf(RuntimeException.class);
    }
}
