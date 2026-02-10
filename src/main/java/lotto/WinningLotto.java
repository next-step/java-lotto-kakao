package lotto;

import java.util.List;

public class WinningLotto {

	private List<Integer> numbers;
	private int bonus;

	WinningLotto(List<Integer> numbers, int bonus) {
		this.numbers = numbers;
		this.bonus = bonus;
	}

	List<Integer> getNumbers() {
		return this.numbers;
	}

	int checkNumbers(List<Integer> lottoNumbers) {
		return (int) lottoNumbers.stream()
			.filter(numbers::contains)
			.count();
	}

	boolean isContainBonus(List<Integer> lottoNumbers) {
		return lottoNumbers.contains(bonus);
	}
}
