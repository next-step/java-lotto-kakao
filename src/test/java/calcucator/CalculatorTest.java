package calcucator;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import calculator.Calculator;
import calculator.Number;

public class CalculatorTest {
	Calculator calculator;

	@BeforeEach
	void setUp() {
		calculator = new Calculator();
	}

	@Test
	public void sumTest() {
		List<Number> list = new ArrayList<>();
		list.add(new Number("1"));
		list.add(new Number("2"));
		list.add(new Number("3"));
		int result = calculator.sum(list);
		assertThat(result).isEqualTo(6);
	}
}
