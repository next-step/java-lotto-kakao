package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningNumbersTest {
	@DisplayName("당첨 번호는 번호와 보너스로 구성되어야 한다")
	@Test
	void of_withNumbersAndBonus_createsWinningNumbers() {
		Lotto numbers = Lotto.from(List.of(1, 2, 3, 4, 5, 6));

		WinningNumbers winningNumbers = WinningNumbers.of(numbers, LottoNumber.from(7));

		assertThat(winningNumbers.getLotto()).isEqualTo(numbers);
		assertThat(winningNumbers.getBonusNumber()).isEqualTo(LottoNumber.from(7));
	}

	@DisplayName("보너스 번호가 당첨 번호와 중복되면 IllegalArgumentException이 발생해야 한다")
	@Test
	void of_withDuplicateBonusNumber_throwsIllegalArgumentException() {
		Lotto numbers = Lotto.from(List.of(1, 2, 3, 4, 5, 6));

		assertThatThrownBy(() -> WinningNumbers.of(numbers, LottoNumber.from(6)))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("당첨 번호가 null이면 IllegalArgumentException이 발생해야 한다")
	@Test
	void of_withNullNumbers_throwsIllegalArgumentException() {
		assertThatThrownBy(() -> WinningNumbers.of(null, LottoNumber.from(7)))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("보너스 번호가 null이면 IllegalArgumentException이 발생해야 한다")
	@Test
	void of_withNullBonusNumber_throwsIllegalArgumentException() {
		Lotto numbers = Lotto.from(List.of(1, 2, 3, 4, 5, 6));

		assertThatThrownBy(() -> WinningNumbers.of(numbers, null))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("비교할 로또가 null이면 IllegalArgumentException이 발생해야 한다")
	@Test
	void match_withNullLotto_throwsIllegalArgumentException() {
		WinningNumbers winningNumbers = WinningNumbers.of(
			Lotto.from(List.of(1, 2, 3, 4, 5, 6)),
			LottoNumber.from(7)
		);

		assertThatThrownBy(() -> winningNumbers.match(null))
			.isInstanceOf(IllegalArgumentException.class);
	}
}
