package calcucator;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class NumberTest {
	@Test
	void emptyReturnZero() {
		String text = "";
		calculator.Number number = new calculator.Number(text);
		assertThat(number.getValue()).isEqualTo(0);
	}

	@Test
	void nullReturnZero() {
		String text = null;
		calculator.Number number = new calculator.Number(text);
		assertThat(number.getValue()).isEqualTo(0);
	}

	@Test
	void StringReturnNumber() {
		String text = "123";
		calculator.Number number = new calculator.Number(text);
	}

	@Test
	void notNumberException() {
		String text = "abc";
		assertThatIllegalArgumentException().isThrownBy(() -> new calculator.Number(text));
	}

	@Test
	void minusNumberException() {
		String text = "-2";
		assertThatIllegalArgumentException().isThrownBy(() -> new calculator.Number(text));
	}
}
