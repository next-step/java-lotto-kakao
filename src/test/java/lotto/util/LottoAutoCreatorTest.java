package lotto.util;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static lotto.support.LottoTestFixture.lottoNumbers;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class LottoAutoCreatorTest {

    @Test
    @DisplayName("정상적으로 로또 생성")
    void duplicatedNumberTest() {
        Set<LottoNumber> lotto = lottoNumbers(1, 2, 3, 4, 5, 6);
        Lotto myLotto = new Lotto(lotto);

        assertThat(myLotto.getLotto()).hasSize(6);
    }

    @Test
    @DisplayName("정상적으로 suffle 실행")
    void createLottoTest() {
        assertDoesNotThrow(LottoAutoCreator::lottoAutoCreate);
    }
}
