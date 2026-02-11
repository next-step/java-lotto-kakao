package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WinningLottoTest {

    Lotto lotto;
    WinningLotto winning;

    @BeforeEach
    void init() {
        lotto = testSetLotto();
        winning = new WinningLotto(testSetWinning(1, 7), new LottoNumber(40));
    }

    @Test
    @DisplayName("일치하는 개수 판단")
    void 일치개수_테스트() {
        WinningLotto winning = new WinningLotto(testSetWinning(20, 26), new LottoNumber(40));
        Assertions.assertThat(winning.judge(lotto)).isEqualTo(Rank.MISS);
    }


    @Test
    void 일등_테스트() {
        Assertions.assertThat(winning.judge(lotto)).isEqualTo(Rank.FIRST);
    }

    Lotto testSetLotto() {
        return new Lotto(IntStream.range(1, 7)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList()));
    }

    Lotto testSetWinning(int start, int end) {
        return new Lotto(IntStream.range(start, end)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList()));
    }
}
