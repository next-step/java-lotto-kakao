package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Lotto 관련 테스트")
class LottoTest {
    @Test
    void 정수_리스트를_입력_받아_생성() {
        Lotto lotto = Lotto.of(List.of(1, 2, 3, 4, 5, 6));
        assertThat(lotto).isNotNull();
    }

    @Test
    void 정수_리스트_입력으로_구성된_Lotto의_값은_정렬된_상태를_유지() {
        Lotto lotto = Lotto.of(List.of(6, 5, 4, 3, 2, 1));
        assertThat(lotto.numbers())
                .isEqualTo(Stream.of(1, 2, 3, 4, 5, 6).map(LottoNumber::of).collect(Collectors.toList()));
    }

    @Test
    void null이_인자로_들어간_Lotto의_생성은_NPE를_발생() {
        assertThatThrownBy(() -> Lotto.of(null)).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @MethodSource("generateMatchingData")
    void 숫자_매칭_개수를_확인(Lotto lotto, WinningLotto winningLotto, int matchCount) {
        assertThat(lotto.matchCount(winningLotto)).isEqualTo(matchCount);
    }

    private static Stream<Arguments> generateMatchingData() {
        Lotto lotto = Lotto.of(List.of(1, 2, 3, 4, 5, 6));
        return Stream.of(
                Arguments.of(lotto, WinningLotto.of(List.of(1, 7, 8, 9, 10, 11), 42), 1),
                Arguments.of(lotto, WinningLotto.of(List.of(1, 2, 8, 9, 10, 11), 42), 2),
                Arguments.of(lotto, WinningLotto.of(List.of(1, 2, 3, 9, 10, 11), 42), 3),
                Arguments.of(lotto, WinningLotto.of(List.of(1, 2, 3, 4, 10, 11), 42), 4),
                Arguments.of(lotto, WinningLotto.of(List.of(1, 2, 3, 4, 5, 11), 42), 5),
                Arguments.of(lotto, WinningLotto.of(List.of(1, 2, 3, 4, 5, 6), 42), 6)
        );
    }

    @Test
    void Bonus_매치_여부를_확인() {
        Lotto lotto = Lotto.of(List.of(1, 2, 3, 4, 5, 6));
        boolean matchBonus = lotto.matchBonus(WinningLotto.of(List.of(4, 5, 6, 7, 8, 9), 1));
        assertThat(matchBonus).isTrue();
    }
}