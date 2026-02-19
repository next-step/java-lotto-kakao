import model.LottoNumber;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestLottoNumber {

    @Test
    void 로또번호_값객체는_1부터_45사이_숫자만_허용한다() {
        assertThatThrownBy(() -> LottoNumber.of(0))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("로또 번호는 1~45까지의 숫자여야 한다.");
    }

    @Test
    void 로또번호_값객체는_동등성_비교가_가능하다() {
        LottoNumber first = LottoNumber.of(3);
        LottoNumber second = LottoNumber.of(3);

        assertThat(first).isEqualTo(second);
    }

    @Test
    void 같은_숫자에_대해_캐싱된_인스턴스를_재사용한다() {
        LottoNumber first = LottoNumber.of(3);
        LottoNumber second = LottoNumber.of(3);

        assertThat(first).isSameAs(second);
    }
}
