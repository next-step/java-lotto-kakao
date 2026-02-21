package domain.lotto;

import domain.winning.LottoResult;
import domain.winning.WinningStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;

public class LottoResultTest {

    @Test
    public void generate_lotto() {
        WinningStatus winningStatus = WinningStatus.FIRST;
        int winningStatusFirstCount = 1;
        EnumMap<WinningStatus, Integer> winningStatusMap = new EnumMap<>(WinningStatus.class);
        winningStatusMap.put(winningStatus, winningStatusFirstCount);
        winningStatusMap.get(winningStatus);

        LottoResult lottoResult = new LottoResult(winningStatusMap);
        Assertions.assertThat(lottoResult.getCounts().get(winningStatus)).isEqualTo(winningStatusFirstCount);
    }
}
