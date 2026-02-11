package lotto;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BallTest {

	@Test
	void compareBall() {
		Ball ball1 = new Ball(1);
		Ball ball2 = new Ball(1);
		assertThat(ball1.equals(ball2)).isTrue();
	}

	@Test
	void 문자가_들어왔을_때() {
		assertThatIllegalArgumentException().isThrownBy(() -> new Ball("a"));
	}

	@Test
	void 범위밖의_숫자가_들어왔을_때() {
		assertThatIllegalArgumentException().isThrownBy(() -> new Ball("46"));
	}
}
