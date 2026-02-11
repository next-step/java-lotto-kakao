package lotto;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import lotto.model.LottoNumber;

public class LottoNumberTest {

	@Test
	void validateLottoNumberCache() {
		List<LottoNumber> lottoNumbers = LottoNumber.getCache();
		assertThat(lottoNumbers.size()).isEqualTo(45);
	}
}
