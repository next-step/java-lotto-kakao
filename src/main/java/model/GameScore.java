package model;

import java.util.List;
import java.util.Objects;

public class GameScore {
	final Integer bonusNumber;
	final List<Integer> winNumbers;

	public GameScore(int bonusNumber, List<Integer> winNumbers) {
		winNumbers.sort(((o1,o2) -> o1 - o2));
		this.bonusNumber = bonusNumber;
		this.winNumbers = winNumbers;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass())
			return false;
		GameScore gameScore = (GameScore)o;
		return Objects.equals(bonusNumber, gameScore.bonusNumber) && Objects.equals(winNumbers,
			gameScore.winNumbers);
	}

	@Override
	public int hashCode() {
		return Objects.hash(bonusNumber, winNumbers);
	}
}
