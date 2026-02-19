package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class WinningLottoTest {

    @Test
    @DisplayName("유효한 당첨로또 생성")
    public void createWinningLotto() {
        LottoNumber bonusNum = LottoNumber.of(33);
        assertThatCode(() -> new WinningLotto(new LottoTicket(1,2,3,4,5,6), bonusNum))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("보너스 번호가 겹친 당첨로또")
    public void InvalidWinningLotto() {
        LottoNumber bonusNum = LottoNumber.of(1);
        assertThatThrownBy(() -> new WinningLotto(new LottoTicket(1,2,3,4,5,6), bonusNum))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }

    @ParameterizedTest(name = "{0}등")
    @DisplayName("로또 등수 판별")
    @MethodSource("rankTestCases")
    public void getWinningRank(String rankName, LottoTicket userTicket, WinningRank expected) {
        WinningLotto winningLotto = new WinningLotto(new LottoTicket(1, 2, 3, 4, 5, 6), LottoNumber.of(8));
        WinningRank rank = winningLotto.rank(userTicket);
        assertThat(rank).isEqualTo(expected);
    }

    static Stream<Arguments> rankTestCases() {
        return Stream.of(
                Arguments.of("1", new LottoTicket(1, 2, 3, 4, 5, 6), WinningRank.FIRST),
                Arguments.of("2", new LottoTicket(1, 2, 3, 4, 5, 8), WinningRank.SECOND),
                Arguments.of("3", new LottoTicket(1, 2, 3, 4, 5, 9), WinningRank.THIRD),
                Arguments.of("4", new LottoTicket(1, 2, 3, 4, 9, 10), WinningRank.FOURTH),
                Arguments.of("5", new LottoTicket(1, 2, 3, 9, 10, 11), WinningRank.FIFTH),
                Arguments.of("꽝", new LottoTicket(7, 8, 9, 10, 11, 12), WinningRank.NONE)
        );
    }
}