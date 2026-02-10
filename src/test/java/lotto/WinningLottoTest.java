package lotto;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class WinningLottoTest {

	List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
	int bonus = 7;
	WinningLotto winningLotto = new WinningLotto(numbers, bonus);

	@Test
	void winningLottoTest() {
		List<Integer> winningLottoNumber = winningLotto.getNumbers();
		assertThat(winningLottoNumber.size()).isEqualTo(6);
	}

	@Test
	void lotteryNumberListCheckerTest() {
		List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		int hits = winningLotto.checkNumbers(lottoNumbers);
		assertThat(hits).isEqualTo(6);
	}

	@Test
	void lotteryBonusCheckerTest() {
		List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 7);
		boolean isContainBonus = winningLotto.isContainBonus(lottoNumbers);
		assertThat(isContainBonus).isEqualTo(true);
	}
}
