package calculator;

import java.util.List;

public class Calculator {
	List<Number> numbers;
	private Parser parser;

	public int sum(List<Number> list) {
		int result = 0;
		for (Number number : list) {
			result += number.getValue();
		}
		return result;
	}
}
