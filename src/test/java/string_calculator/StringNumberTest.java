package string_calculator;


import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

public class StringNumberTest {
	@Test
	void isStringSingleDigit() {
		StringNumber stringNumber = new StringNumber("1");
		assertThat(stringNumber.value).isEqualTo(BigInteger.ONE);
	}

	@Test
	void isStringMultipleDigit() {
		StringNumber stringNumber = new StringNumber("123");
		assertThat(stringNumber.value).isEqualTo(new BigInteger("123"));
	}

	@Test
	void isStringCharacter() {
		assertThatThrownBy(() -> new StringNumber("abc"))
			.isInstanceOf(RuntimeException.class);
	}
}
