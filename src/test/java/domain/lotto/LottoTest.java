package domain.lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LottoTest {

    @Test
    @DisplayName("자동 로또 생성한다.")
    void create_auto_lotto() {
        Random random = new Random(10);
        LottoFactory autoLottoFactory = new AutoLottoFactory(5, random);
        Lotto lotto = autoLottoFactory.create().getFirst();
        List<LottoNumber> lottoNumbers = lotto.getNumbers();

        assertEquals(6, new HashSet<>(lottoNumbers).size());
    }

    @Test
    @DisplayName("수동 로또 하나를 생성한다.")
    void create_manual_lotto() {
        LottoFactory manualLottoFactory = new ManualLottoFactory(List.of(List.of(3, 11, 15, 29, 35, 44)));
        Lotto lotto = manualLottoFactory.create().getFirst();
        List<LottoNumber> lottoNumbers = lotto.getNumbers();

        assertEquals(6, new HashSet<>(lottoNumbers).size());
    }
}
