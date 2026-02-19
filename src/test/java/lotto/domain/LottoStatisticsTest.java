package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoStatisticsTest {
	@DisplayName("로또와 당첨 번호로 결과별 개수를 집계해야 한다")
	@Test
	void of_withLottosAndWinningNumbers_countsEachResult() {
		WinningNumbers winningNumbers = WinningNumbers.of(
			Lotto.from(List.of(1, 2, 3, 4, 5, 6)),
			LottoNumber.from(7)
		);
		List<Lotto> lottos = List.of(
			Lotto.from(List.of(1, 2, 3, 4, 5, 6)),
			Lotto.from(List.of(1, 2, 3, 4, 5, 7)),
			Lotto.from(List.of(1, 2, 3, 4, 5, 8)),
			Lotto.from(List.of(1, 2, 3, 4, 7, 10)),
			Lotto.from(List.of(1, 2, 3, 4, 9, 10)),
			Lotto.from(List.of(1, 2, 3, 9, 10, 11)),
			Lotto.from(List.of(8, 9, 10, 11, 12, 13))
		);

		LottoStatistics statistics = LottoStatistics.of(createPurchase(lottos, 7_000), winningNumbers);

		assertThat(statistics.getCounts().get(LottoResult.SIX_MATCH)).isEqualTo(1);
		assertThat(statistics.getCounts().get(LottoResult.FIVE_MATCH_WITH_BONUS)).isEqualTo(1);
		assertThat(statistics.getCounts().get(LottoResult.FIVE_MATCH)).isEqualTo(1);
		assertThat(statistics.getCounts().get(LottoResult.FOUR_MATCH)).isEqualTo(2);
		assertThat(statistics.getCounts().get(LottoResult.THREE_MATCH)).isEqualTo(1);
	}

	@DisplayName("구입 금액으로 수익률을 계산해야 한다")
	@Test
	void profitRate_withPurchaseAmount_returnsProfitRate() {
		WinningNumbers winningNumbers = WinningNumbers.of(
			Lotto.from(List.of(1, 2, 3, 4, 5, 6)),
			LottoNumber.from(7)
		);
		List<Lotto> lottos = List.of(
			Lotto.from(List.of(1, 2, 3, 4, 5, 6)),
			Lotto.from(List.of(1, 2, 3, 4, 5, 7)),
			Lotto.from(List.of(1, 2, 3, 4, 9, 10)),
			Lotto.from(List.of(1, 2, 3, 9, 10, 11)),
			Lotto.from(List.of(8, 9, 10, 11, 12, 13))
		);

		LottoStatistics statistics = LottoStatistics.of(createPurchase(lottos, 10_000), winningNumbers);

		assertThat(statistics.getProfitRate()).isEqualTo(203_005.5);
	}

	private LottoPurchase createPurchase(List<Lotto> lottos, int amount) {
		return LottoPurchase.of(lottos, 0, lottos.size(), amount);
	}
}
