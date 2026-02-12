package model;

import java.util.List;
import java.util.Objects;

public class LotteryWinningNumbers {
	private final Integer bonusNumber;
	private final List<Integer> winNumbers;

	public LotteryWinningNumbers(int bonusNumber, List<Integer> winNumbers) {
		winNumbers.sort(((o1,o2) -> o1 - o2));
		this.bonusNumber = bonusNumber;
		this.winNumbers = winNumbers;
	}

	public Integer getBonusNumber() {
		return bonusNumber;
	}

	public List<Integer> getWinNumbers() {
		return winNumbers;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass())
			return false;
		LotteryWinningNumbers lotteryWinningNumbers = (LotteryWinningNumbers)o;
		return Objects.equals(bonusNumber, lotteryWinningNumbers.bonusNumber) && Objects.equals(winNumbers,
			lotteryWinningNumbers.winNumbers);
	}

	@Override
	public int hashCode() {
		return Objects.hash(bonusNumber, winNumbers);
	}
}
