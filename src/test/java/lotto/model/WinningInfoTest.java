package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WinningInfoTest {

    @Test
    @DisplayName("로또 단건 결과 저장")
    public void CreateWinningInfo() {
        WinningInfo winningInfo = new WinningInfo();
        winningInfo.addResult(WinningRank.THIRD);
        assertThat(winningInfo.totalPrice()).isEqualTo(new Money(1_500_000));
    }

    @Test
    @DisplayName("로또 여러 건 결과 저장")
    public void CreateWinningInfos() {
        WinningInfo winningInfo = new WinningInfo();
        winningInfo.addResult(WinningRank.THIRD);
        winningInfo.addResult(WinningRank.FIRST);
        winningInfo.addResult(WinningRank.NONE);

        assertThat(winningInfo.totalPrice()).isEqualTo(new Money(2_001_500_000));
    }

    @Test
    @DisplayName("통계 문자열 생성")
    public void statisticsString() {
        WinningInfo winningInfo = new WinningInfo();
        winningInfo.addResult(WinningRank.SECOND);

        String stats = winningInfo.statisticsString();

        assertThat(stats).contains("당첨 통계");
        assertThat(stats).contains("5개 일치, 보너스 볼 일치");
    }

}
