package domain.lotto;

import domain.winning.LottoResult;
import domain.winning.WinningLotto;
import domain.winning.WinningStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoGroupTest {

    @DisplayName("당첨 결과를 등수별로 집계한다")
    @Test
    void compare_returns_aggregated_result_counts() {
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        LottoGroup lottoGroup = new LottoGroup(List.of(
                createLotto(1, 2, 3, 4, 5, 6),   // FIRST
                createLotto(1, 2, 3, 4, 5, 7),   // SECOND
                createLotto(1, 2, 3, 4, 5, 8),   // THIRD
                createLotto(1, 2, 3, 4, 8, 9),   // FOURTH
                createLotto(1, 2, 3, 10, 11, 12),// FIFTH
                createLotto(8, 9, 10, 11, 12, 13)// FAIL
        ));

        LottoResult result = lottoGroup.compare(winningLotto);

        assertThat(result.getCounts().get(WinningStatus.FIRST)).isEqualTo(1);
        assertThat(result.getCounts().get(WinningStatus.SECOND)).isEqualTo(1);
        assertThat(result.getCounts().get(WinningStatus.THIRD)).isEqualTo(1);
        assertThat(result.getCounts().get(WinningStatus.FOURTH)).isEqualTo(1);
        assertThat(result.getCounts().get(WinningStatus.FIFTH)).isEqualTo(1);
        assertThat(result.getCounts().get(WinningStatus.FAIL)).isEqualTo(1);
    }

    @DisplayName("concat은 두 그룹의 로또를 순서대로 합친 새 그룹을 반환한다")
    @Test
    void concat_returns_new_group_with_merged_lottos() {
        LottoGroup first = new LottoGroup(List.of(
                createLotto(1, 2, 3, 4, 5, 6),
                createLotto(7, 8, 9, 10, 11, 12)
        ));
        LottoGroup second = new LottoGroup(List.of(
                createLotto(13, 14, 15, 16, 17, 18)
        ));

        LottoGroup merged = first.concat(second);

        assertThat(merged.getSize()).isEqualTo(3);
    }

    private Lotto createLotto(int... numbers) {
        List<LottoNumber> lottoNumbers = java.util.Arrays.stream(numbers)
                .mapToObj(LottoNumber::new)
                .toList();
        return new Lotto(lottoNumbers);
    }
}
