import domains.AutoLottoGenerator;
import domains.Lotto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AutoLottoGeneratorTest {
    @Test
    void 지정된_갯수만큼_자동으로_로또를_생성한다() {
        int count = 5;
        AutoLottoGenerator generator = new AutoLottoGenerator(count);

        List<Lotto> lottos = generator.generate();

        assertThat(lottos).hasSize(count);
    }

    @Test
    void 생성된_자동_로또_정상_반환_확인() {
        AutoLottoGenerator generator = new AutoLottoGenerator(1);

        List<Lotto> lottos = generator.generate();

        assertThat(lottos.get(0)).isNotNull();
    }
}