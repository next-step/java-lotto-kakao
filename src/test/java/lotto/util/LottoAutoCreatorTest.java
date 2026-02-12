package lotto.util;

import lotto.domain.LottoBalls;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class LottoAutoCreatorTest {

    @Test
    @DisplayName("정상적으로 로또 생성")
    void duplicatedNumberTest() {
        Set<Integer> lotto = new HashSet<>(List.of(1, 2, 3, 4, 5, 6));
        LottoBalls myLotto = new LottoBalls(lotto);

        assertThat(myLotto.getLottoBalls()).hasSize(6);
    }

    @Test
    @DisplayName("정상적으로 suffle 실행")
    void createLottoTest() {
        assertDoesNotThrow(() -> {
            new LottoAutoCreator().lottoCreate();
        });
    }
}
