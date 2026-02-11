package calcucator;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import calculator.Parser;

public class ParserTest {
	Parser parser;

	@BeforeEach
	void setUp() {
		parser = new Parser();
	}

	@Test
	void splitTest() {
		String input = "1,2";
		String[] expected = {"1", "2"};
		String[] actual = parser.split(input);
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	void splitTest2() {
		String input = "1:2";
		String[] expected = {"1", "2"};
		String[] actual = parser.split(input);
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	void splitTest3() {
		String input = "1:2,3";
		String[] expected = {"1", "2", "3"};
		String[] actual = parser.split(input);
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	void splitTestWithCustomDelimiter() {
		String input = "//;\n1;2,3";
		String[] expected = {"1", "2", "3"};
		String[] actual = parser.split(input);
		assertThat(actual).isEqualTo(expected);
	}
}
