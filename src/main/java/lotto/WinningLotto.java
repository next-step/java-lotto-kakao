package lotto;

public class WinningLotto {

	private final Lotto winningNumbers;
	private final LottoNumber bonus;

	WinningLotto(Lotto winningNumbers, LottoNumber bonus) {
		this.winningNumbers = winningNumbers;
		this.bonus = bonus;
	}

	int checkNumbers(Lotto tickets) {
		return winningNumbers.countMatch(tickets);
	}

	boolean isContainBonus(Lotto tickets) {
		return tickets.isContainBonusBall(bonus);
	}

	public MatchCount aggreateMatchCount(Lotto tickets) {
		int count = checkNumbers(tickets);
		boolean isContainBonus = isContainBonus(tickets);
		if (count == MatchCount.SIX.getCount()) {
			return MatchCount.SIX;
		}
		if (count == MatchCount.FIVE_BONUS.getCount() && isContainBonus == MatchCount.FIVE_BONUS.hasBonus()) {
			return MatchCount.FIVE_BONUS;
		}
		if (count == MatchCount.FIVE.getCount()) {
			return MatchCount.FIVE;
		}
		if (count == MatchCount.FOUR.getCount()) {
			return MatchCount.FOUR;
		}
		if (count == MatchCount.THREE.getCount()) {
			return MatchCount.THREE;
		}
		return MatchCount.NOTHING;
	}
}
