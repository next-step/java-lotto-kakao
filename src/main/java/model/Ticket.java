package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ticket {
	private final List<Integer> numbers;
	private final Long key;
	Ticket(Integer... numbers) {
		this(new ArrayList<>(Arrays.asList(numbers)));
	}

	Ticket(List<Integer> numbers) {
		numbers.sort(((o1, o2) -> o1 - o2));
		long key = 0L;
		for(Integer number: numbers) {
			key |= (1L << number);
		}
		this.numbers = numbers;
		this.key = key;
	}

	public List<Integer> get() {
		return numbers;
	}
	public Long getKey() {
		return key;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("[");
		for (Integer number : numbers) {
			stringBuilder.append(number).append(", ");
		}
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		stringBuilder.append("]");
		return stringBuilder.toString();
	}
}
