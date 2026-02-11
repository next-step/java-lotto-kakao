package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {
	@DisplayName("일치 개수와 보너스 여부로 등수가 결정되어야 한다")
	@Test
	void of_withMatchCountAndBonus_returnsExpectedResult() {
		assertThat(LottoResult.of(3, false)).contains(LottoResult.THREE_MATCH);
		assertThat(LottoResult.of(4, false)).contains(LottoResult.FOUR_MATCH);
		assertThat(LottoResult.of(5, false)).contains(LottoResult.FIVE_MATCH);
		assertThat(LottoResult.of(5, true)).contains(LottoResult.FIVE_MATCH_WITH_BONUS);
		assertThat(LottoResult.of(6, false)).contains(LottoResult.SIX_MATCH);
		assertThat(LottoResult.of(2, false)).isEmpty();
	}
}
