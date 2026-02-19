package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
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

		LottoStatistics statistics = LottoStatistics.of(lottos, winningNumbers);

		assertThat(statistics.getCounts().get(LottoResult.SIX_MATCH)).isEqualTo(1);
		assertThat(statistics.getCounts().get(LottoResult.FIVE_MATCH_WITH_BONUS)).isEqualTo(1);
		assertThat(statistics.getCounts().get(LottoResult.FIVE_MATCH)).isEqualTo(1);
		assertThat(statistics.getCounts().get(LottoResult.FOUR_MATCH)).isEqualTo(2);
		assertThat(statistics.getCounts().get(LottoResult.THREE_MATCH)).isEqualTo(1);
	}

	@DisplayName("로또와 당첨 번호로 총 당첨금을 계산해야 한다")
	@Test
	void of_withLottosAndWinningNumbers_calculatesTotalPrize() {
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

		LottoStatistics statistics = LottoStatistics.of(lottos, winningNumbers);

		assertThat(statistics.getTotalPrize()).isEqualTo(2_030_055_000L);
	}

	@DisplayName("구입 금액으로 수익률을 계산해야 한다")
	@Test
	void getProfitRate_withPurchaseAmount_returnsProfitRate() {
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

		LottoStatistics statistics = LottoStatistics.of(lottos, winningNumbers);

		assertThat(statistics.getProfitRate(10_000)).isEqualTo(203_005.5);
	}

	@DisplayName("집계할 로또 목록이 null이면 IllegalArgumentException이 발생해야 한다")
	@Test
	void of_withNullLottos_throwsIllegalArgumentException() {
		WinningNumbers winningNumbers = WinningNumbers.of(
			Lotto.from(List.of(1, 2, 3, 4, 5, 6)),
			LottoNumber.from(7)
		);

		assertThatThrownBy(() -> LottoStatistics.of(null, winningNumbers))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("당첨 번호가 null이면 IllegalArgumentException이 발생해야 한다")
	@Test
	void of_withNullWinningNumbers_throwsIllegalArgumentException() {
		List<Lotto> lottos = List.of(Lotto.from(List.of(1, 2, 3, 4, 5, 6)));

		assertThatThrownBy(() -> LottoStatistics.of(lottos, null))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("집계할 로또 목록에 null이 포함되면 IllegalArgumentException이 발생해야 한다")
	@Test
	void of_withNullLottoElement_throwsIllegalArgumentException() {
		WinningNumbers winningNumbers = WinningNumbers.of(
			Lotto.from(List.of(1, 2, 3, 4, 5, 6)),
			LottoNumber.from(7)
		);
		List<Lotto> lottos = new ArrayList<>();
		lottos.add(Lotto.from(List.of(1, 2, 3, 4, 5, 6)));
		lottos.add(null);

		assertThatThrownBy(() -> LottoStatistics.of(lottos, winningNumbers))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("구입 금액이 0 이하이면 IllegalArgumentException이 발생해야 한다")
	@Test
	void getProfitRate_withNonPositivePurchaseAmount_throwsIllegalArgumentException() {
		WinningNumbers winningNumbers = WinningNumbers.of(
			Lotto.from(List.of(1, 2, 3, 4, 5, 6)),
			LottoNumber.from(7)
		);
		List<Lotto> lottos = List.of(Lotto.from(List.of(1, 2, 3, 4, 5, 6)));
		LottoStatistics statistics = LottoStatistics.of(lottos, winningNumbers);

		assertThatThrownBy(() -> statistics.getProfitRate(0))
			.isInstanceOf(IllegalArgumentException.class);
		assertThatThrownBy(() -> statistics.getProfitRate(-1_000))
			.isInstanceOf(IllegalArgumentException.class);
	}

}
