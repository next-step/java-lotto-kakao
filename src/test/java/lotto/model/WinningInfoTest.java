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
        assertThat(winningInfo.getTotalPrice()).isEqualTo(WinningRank.THIRD.winningPrice);
    }

    @Test
    @DisplayName("로또 여러 건 결과 저장")
    public void CreateWinningInfos() {
        WinningInfo winningInfo = new WinningInfo();
        winningInfo.addResult(WinningRank.THIRD);
        winningInfo.addResult(WinningRank.FIRST);
        winningInfo.addResult(WinningRank.NONE);

        assertThat(winningInfo.getTotalPrice()).isEqualTo(WinningRank.THIRD.winningPrice.sum(WinningRank.FIRST.winningPrice));
    }

}