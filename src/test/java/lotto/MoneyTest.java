package lotto;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTest {

	@Test
	void 유효한_금액_생성() {
		Money money = new Money("5000");
		assertThat(money.getPrice()).isEqualTo(5000L);
	}

	@Test
	void 최소_금액_생성() {
		Money money = new Money("1000");
		assertThat(money.getPrice()).isEqualTo(1000L);
	}

	@Test
	void 큰_금액_생성() {
		Money money = new Money("100000");
		assertThat(money.getPrice()).isEqualTo(100000L);
	}

	@ParameterizedTest
	@ValueSource(strings = {"1000", "2000", "5000", "10000", "50000", "100000"})
	void 천원_배수_금액_생성(String input) {
		Money money = new Money(input);
		assertThat(money.getPrice()).isEqualTo(Long.parseLong(input));
	}

	@Test
	void 영원_입력_실패() {
		assertThatThrownBy(() -> new Money("0"))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("잘못된 구입 금액입니다.");
	}

	@Test
	void 음수_금액_입력_실패() {
		assertThatThrownBy(() -> new Money("-5000"))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("잘못된 구입 금액입니다.");
	}

	@Test
	void 천원_미만_금액_입력_실패() {
		assertThatThrownBy(() -> new Money("500"))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("잘못된 구입 금액입니다.");
	}

	@ParameterizedTest
	@ValueSource(strings = {"1500", "2500", "4500", "5500", "9999"})
	void 천원_배수가_아닌_금액_실패(String input) {
		assertThatThrownBy(() -> new Money(input))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("잘못된 구입 금액입니다.");
	}

	@Test
	void 정수가_아닌_입력_실패() {
		assertThatThrownBy(() -> new Money("5000.5"))
			.isInstanceOf(NumberFormatException.class);
	}

	@Test
	void 문자_포함_입력_실패() {
		assertThatThrownBy(() -> new Money("5000a"))
			.isInstanceOf(NumberFormatException.class);
	}

	@Test
	void 빈_문자열_입력_실패() {
		assertThatThrownBy(() -> new Money(""))
			.isInstanceOf(NumberFormatException.class);
	}

	@Test
	void 공백만_입력_실패() {
		assertThatThrownBy(() -> new Money("   "))
			.isInstanceOf(NumberFormatException.class);
	}
}

