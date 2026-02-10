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
}
