package lotto;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class WinningLottoTest {

	@Test
	void winningLottoTest() {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		int bonus = 7;
		WinningLotto winningLotto = new WinningLotto(numbers, bonus);
		List<Integer> winningLottoNumber = winningLotto.getNumbers();
		assertThat(winningLottoNumber.size()).isEqualTo(6);
	}
}
