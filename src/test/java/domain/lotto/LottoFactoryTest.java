package domain.lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class LottoFactoryTest {

    @Test
    void generate_manual_lotto() {
        int count = 1;
        List<Integer> numbers = List.of(6,21,23,24,37,41);
        LottoFactory manualLottoFactory = new ManualLottoFactory(List.of(numbers));

        List<Lotto> lottos = manualLottoFactory.create();

        Assertions.assertThat(lottos.size()).isEqualTo(count);
    }

    @Test
    void generate_auto_lotto() {
        int count = 2;
        LottoFactory autoLottoFactory = new AutoLottoFactory(count);

        List<Lotto> lottos = autoLottoFactory.create();

        Assertions.assertThat(lottos.size()).isEqualTo(count);
    }
}