package lotto;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CountTest {
	@Test
	@DisplayName("Count는 0 이상의 값으로만 생성할 수 있다")
	void create_count() {
		assertThat(new Count(0)).isEqualTo(new Count(0));
		assertThat(new Count(3)).isEqualTo(new Count(3));
	}

	@Test
	@DisplayName("Count는 음수로 생성할 수 없다")
	void create_fail_when_negative_value() {
		assertThatThrownBy(() -> new Count(-1))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("Count는 덧셈 연산을 지원한다")
	void add() {
		Count left = new Count(3);
		Count right = new Count(2);

		assertThat(left.add(right)).isEqualTo(new Count(5));
	}

	@Test
	@DisplayName("Count는 뺄셈 연산을 지원한다")
	void subtract() {
		Count left = new Count(5);
		Count right = new Count(2);

		assertThat(left.subtract(right)).isEqualTo(new Count(3));
	}

	@Test
	@DisplayName("Count 뺄셈 결과가 음수면 예외가 발생한다")
	void subtract_fail_when_result_is_negative() {
		Count left = new Count(2);
		Count right = new Count(5);

		assertThatThrownBy(() -> left.subtract(right))
			.isInstanceOf(IllegalArgumentException.class);
	}
}
