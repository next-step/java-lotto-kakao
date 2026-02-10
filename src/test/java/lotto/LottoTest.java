package lotto;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import org.junit.jupiter.api.Test;

public class LottoTest {

	@Test
	void createLottoTest() {
		Lotto lotto = new Lotto();
		List<Integer> lottoNumbers = lotto.getNumbers();
		assertThat(lottoNumbers.size()).isEqualTo(6);
	}

	@Test
	void isAscending() {
		Lotto lotto = new Lotto();
		List<Integer> lottoNumbers = lotto.getNumbers();
		int size = lottoNumbers.size();
		for (int i = 0; i < size - 1; i++) {
			assertThat(lottoNumbers.get(i) < lottoNumbers.get(i + 1)).isEqualTo(true);
		}
	}
}
