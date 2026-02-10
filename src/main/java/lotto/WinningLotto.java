package lotto;

import java.util.List;

public class WinningLotto {

	private final List<Integer> numbers;
	private final int bonus;

	WinningLotto(List<Integer> numbers, int bonus) {
		// Todo: validate 메소드 생성
		this.numbers = numbers;
		this.bonus = bonus;
	}

	List<Integer> getNumbers() {
		return this.numbers;
	}

	int checkNumbers(Lotto lotto) {
		return (int) lotto.getNumbers().stream()
			.filter(numbers::contains)
			.count();
	}

	boolean isContainBonus(Lotto lotto) {
		return lotto.getNumbers().contains(bonus);
	}
}
