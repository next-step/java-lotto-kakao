import domain.lotto.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LottoTest {

    @Test
    @DisplayName("자동 로또 하나를 생성한다.")
    void create_auto_lotto() {
        Random random = new Random(10);
        LottoFactory autoLottoFactory = new LottoFactory(random);
        Lotto lotto = autoLottoFactory.createAutoLotto();
        List<LottoNumber> lottoNumbers = lotto.getNumbers();

        assertEquals(6, new HashSet<>(lottoNumbers).size());
    }

    @Test
    @DisplayName("수동 로또 하나를 생성한다.")
    void create_manual_lotto() {
        LottoFactory manualLottoFactory = new LottoFactory();
        Lotto lotto = manualLottoFactory.createManualLotto(List.of(3, 11, 15, 29, 35, 44));
        List<LottoNumber> lottoNumbers = lotto.getNumbers();

        assertEquals(6, new HashSet<>(lottoNumbers).size());
    }

    @Test
    @DisplayName("자동 로또 여러 개를 생성한다")
    void create_auto_lottos() {
        Random random = new Random(10);
        LottoFactory lottoFactory = new LottoFactory(random);

        LottoIssuer lottoIssuer = new LottoIssuer(lottoFactory);
        LottoGroup lottoGroup = lottoIssuer.issueAuto(5000);

        for (Lotto lotto : lottoGroup.getLottos()) {
            List<LottoNumber> lottoNumbers = lotto.getNumbers();
            assertEquals(6, new HashSet<>(lottoNumbers).size());
        }
        assertEquals(5, lottoGroup.getLottos().size());
    }

}
