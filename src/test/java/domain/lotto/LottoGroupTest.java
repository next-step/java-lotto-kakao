package domain.lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LottoGroupTest {

    @Test
    void generate_manual_lotto() {
        List<Lotto> manualLottos = new ArrayList<>();
        manualLottos.add(new Lotto(List.of(1, 2, 3, 4, 5, 6)));

        LottoGroup lottoGroup = new LottoGroup(manualLottos, Collections.emptyList());

        Assertions.assertThat(lottoGroup.getManualLottosSize()).isEqualTo(1);
        Assertions.assertThat(lottoGroup.getAutoLottos().size()).isZero();
    }

    @Test
    void generate_auto_lotto() {
        LottoFactory autoLottoFactory = new AutoLottoFactory(1);
        List<Lotto> autoLottos = autoLottoFactory.create();

        LottoGroup lottoGroup = new LottoGroup(Collections.emptyList(), autoLottos);

        Assertions.assertThat(lottoGroup.getAutoLottosSize()).isEqualTo(1);
        Assertions.assertThat(lottoGroup.getManualLottos().size()).isZero();
    }
}
