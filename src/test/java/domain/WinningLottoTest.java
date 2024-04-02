package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("WinningLotto 관련 테스트")
public class WinningLottoTest {
    @Test
    void WinningLotto는_Lotto와_LottoNumber를_이용하여_생성() {
        WinningLotto winning = WinningLotto.of(
                List.of(1, 2, 3, 4, 5, 6),
                42
        );
        assertThat(winning).isNotNull();
        assertThat(winning.numbers()).isNotNull();
        assertThat(winning.numbers()).isEqualTo(Lotto.of(List.of(1, 2, 3, 4, 5, 6)));
        assertThat(winning.bonus()).isEqualTo(LottoNumber.of(42));
    }

    @Test
    void Reflection을_이용하여_강제로_null을_주입하여도_검증_로직에_따라_NPE를_발생() throws NoSuchMethodException {
        Constructor<WinningLotto> constructor = WinningLotto.class.getDeclaredConstructor(
                Lotto.class,
                LottoNumber.class
        );
        constructor.setAccessible(true);
        assertThatThrownBy(() -> constructor.newInstance(null, null))
                .isInstanceOf(InvocationTargetException.class);
    }
}
