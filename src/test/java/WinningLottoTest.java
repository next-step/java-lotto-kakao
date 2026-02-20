import domains.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class WinningLottoTest {
    @Test
    void 당첨_번호와_보너스_번호가_중복되면_예외가_발생한다() {
        Lotto winningNumbers = createLotto(1, 2, 3, 4, 5, 6);
        LottoNumber bonusNumber = new LottoNumber(6); // 6이 중복됨!

        assertThatThrownBy(() -> new WinningLotto(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복");
    }

    @Test
    void 당첨_번호와_보너스_번호가_중복되지_않으면_정상적으로_생성된다() {
        Lotto winningNumbers = createLotto(1, 2, 3, 4, 5, 6);
        LottoNumber bonusNumber = new LottoNumber(7); // 중복되지 않음

        assertDoesNotThrow(() -> new WinningLotto(winningNumbers, bonusNumber));
    }


    // 테스트를 위한 유틸리티 메서드
    private Lotto createLotto(int... numbers) {
        List<LottoNumber> lottoNumbers = Arrays.stream(numbers)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList());
        return new Lotto(lottoNumbers);
    }
}
