import model.CompositeLottosGenerator;
import model.Lotto;
import model.Lottos;
import model.LottosGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestCompositeLottosGenerator {
    @Test
    void 여러_생성기의_로또를_순서대로_합성한다() {
        LottosGenerator firstGenerator = () -> {
            Lottos lottos = new Lottos();
            lottos.add(new Lotto(1, 2, 3, 4, 5, 6));
            return lottos;
        };

        LottosGenerator secondGenerator = () -> {
            Lottos lottos = new Lottos();
            lottos.add(new Lotto(7, 8, 9, 10, 11, 12));
            return lottos;
        };

        Lottos lottos = new CompositeLottosGenerator(firstGenerator, secondGenerator).generate();

        assertThat(lottos.getQuantity()).isEqualTo(2);
        assertThat(lottos.getLottoList().get(0).getNumbers()).isEqualTo(List.of(1, 2, 3, 4, 5, 6));
        assertThat(lottos.getLottoList().get(1).getNumbers()).isEqualTo(List.of(7, 8, 9, 10, 11, 12));
    }
}
