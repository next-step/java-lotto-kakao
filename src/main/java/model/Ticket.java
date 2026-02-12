package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ticket {

	private final List<Integer> numbers;

	public Ticket(Integer... numbers) {
		this(new ArrayList<>(Arrays.asList(numbers)));
	}

	public Ticket(List<Integer> numbers) {
		numbers.sort(((o1, o2) -> o1 - o2));
		this.numbers = numbers;
	}
	
	public WinLevel getWinLevel(LotteryWinningNumbers lotteryWinningNumbers) {
		long key = 0L;
		for(Integer number: numbers) {
			key |= (1L << number);
		}
		int winMatchcount = 0;
		for(var winNumber: lotteryWinningNumbers.getWinNumbers()) {
			winMatchcount += (key & (1L <<winNumber)) == 0 ? 0 : 1;
		}
		boolean bonusMatched = (key & (1L << lotteryWinningNumbers.getBonusNumber())) > 0;
		return WinLevel.make(winMatchcount, bonusMatched);
	}

	public List<Integer> getNumbers() {
		return numbers;
	}
}
