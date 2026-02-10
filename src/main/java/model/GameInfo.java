package model;

import java.util.List;
import java.util.Objects;

public class GameInfo {
	final Integer bonusNumber;
	final List<Integer> winNumbers;

	GameInfo(int bonusNumber, List<Integer> winNumbers) {
		winNumbers.sort(((o1,o2) -> o1 - o2));
		this.bonusNumber = bonusNumber;
		this.winNumbers = winNumbers;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass())
			return false;
		GameInfo gameInfo = (GameInfo)o;
		return Objects.equals(bonusNumber, gameInfo.bonusNumber) && Objects.equals(winNumbers,
			gameInfo.winNumbers);
	}

	@Override
	public int hashCode() {
		return Objects.hash(bonusNumber, winNumbers);
	}
}
