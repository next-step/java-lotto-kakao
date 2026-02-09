package string_calculator;

import java.math.BigInteger;

public class StringNumber {
	public BigInteger value;

	StringNumber(String string) {
		this.value = new BigInteger(string);
	}

	public BigInteger getValue() {
		return this.value;
	}
}
