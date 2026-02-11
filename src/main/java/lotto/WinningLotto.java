package lotto;

public class WinningLotto {

	private final Lotto winningNumbers;
	private final LottoNumber bonus;

	WinningLotto(Lotto winningNumbers, LottoNumber bonus) {
		this.winningNumbers = winningNumbers;
		this.bonus = bonus;
	}

	Lotto getNumbers() {
		return this.winningNumbers;
	}

	int checkNumbers(Lotto tickets) {
		return winningNumbers.countMatch(tickets);
	}

	boolean isContainBonus(Lotto tickets) {
		return tickets.isContainBonusBall(bonus);
	}
}
