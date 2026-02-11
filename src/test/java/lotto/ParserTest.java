package lotto;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParserTest {
	Parser p;

	@BeforeEach
	void setUp() {
		p = new Parser();
	}

	@Test
	void 구분자가_잘못되면_예외를_던진다() {
		String input = "1, 2, 3, 4; 5, 6";
		assertThatIllegalArgumentException().isThrownBy(() -> p.parse(input));

	}
}
