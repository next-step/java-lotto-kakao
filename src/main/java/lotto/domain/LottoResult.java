package lotto.domain;

import java.util.Optional;

import lombok.Getter;

@Getter
public enum LottoResult {
	THREE_MATCH(3, false, 5_000L),
	FOUR_MATCH(4, false, 50_000L),
	FIVE_MATCH(5, false, 1_500_000L),
	FIVE_MATCH_WITH_BONUS(5, true, 30_000_000L),
	SIX_MATCH(6, false, 2_000_000_000L);

	private final int matchCount;
	private final boolean bonusMatch;
	private final long prize;

	LottoResult(int matchCount, boolean bonusMatch, long prize) {
		this.matchCount = matchCount;
		this.bonusMatch = bonusMatch;
		this.prize = prize;
	}

	public static Optional<LottoResult> of(int matchCount, boolean bonusMatch) {
		return matchResult(matchCount, bonusMatch);
	}

	private static Optional<LottoResult> matchResult(int matchCount, boolean bonusMatch) {
		return switch (matchCount) {
			case 6 -> Optional.of(SIX_MATCH);
			case 5 -> Optional.of(bonusMatch ? FIVE_MATCH_WITH_BONUS : FIVE_MATCH);
			case 4 -> Optional.of(FOUR_MATCH);
			case 3 -> Optional.of(THREE_MATCH);
			default -> Optional.empty();
		};
	}
}
