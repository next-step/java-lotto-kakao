import model.Lotto;
import model.LottoNumber;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class TestLotto {

    @Test
    void 로또_번호는_6개의_수로_이루어져야_한다() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(numbers);

        assertThat(lotto.size()).isEqualTo(6);
    }

    @Test
    void 로또_번호가_6개의_수가_아닐때_에러를_Throw() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        try {
            Lotto lotto = new Lotto(numbers);
            fail();
        }
        catch (RuntimeException e) {
            assertThat(e.getMessage()).isEqualTo("로또 번호 개수가 "+ LottoNumber.LOTTO_SIZE +"개가 아닙니다.");
        }
    }

    @Test
    void 로또_번호는_모두_달라야_한다() {
        List<Integer> numbers = List.of(1, 2, 2, 3, 4, 5);

        try {
            Lotto lotto = new Lotto(numbers);
            fail();
        }
        catch (RuntimeException e){
            assertThat(e.getMessage()).isEqualTo("로또 번호는 중복될 수 없습니다");
        }
    }

    @Test
    void 로또_번호가_정해진_범위에서_벗어나면_예외가_발생한다() {
        // 0이 포함된 경우 (하한 경계)
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 0, 4, 5)))
            .isInstanceOf(RuntimeException.class)
            .hasMessage("로또 번호는 "+LottoNumber.MIN_NUMBER+"~"+LottoNumber.MAX_NUMBER+"까지의 숫자여야 한다.");

        // 46이 포함된 경우 (상한 경계)
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 46, 4, 5)))
            .isInstanceOf(RuntimeException.class)
            .hasMessage("로또 번호는 "+LottoNumber.MIN_NUMBER+"~"+LottoNumber.MAX_NUMBER+"까지의 숫자여야 한다.");
    }
}
