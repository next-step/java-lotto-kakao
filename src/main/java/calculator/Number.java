package calculator;

public class Number {
	private int value = 0;

	public Number(String text) {
		if (text == null || text.isEmpty()) {
			value = 0;
			return;
		}
		parseInt(text);
		if (value < 0)
			throw new IllegalArgumentException();

	}

	private void parseInt(String text) {
		try {
			this.value = Integer.parseInt(text);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException();
		}
	}

	public int getValue() {
		return value;
	}
}
