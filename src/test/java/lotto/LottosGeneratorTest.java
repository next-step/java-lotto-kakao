package lotto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottosGeneratorTest {
    @Test
    void autoGeneratorCreatesRequestedCount() {
        LottosGenerator generator = new AutoLottosGenerator(5);
        List<Lotto> lottos = generator.generate();
        assertThat(lottos).hasSize(5);
    }

    @Test
    void autoGeneratorCreatesZeroWhenCountIsZero() {
        LottosGenerator generator = new AutoLottosGenerator(0);
        List<Lotto> lottos = generator.generate();
        assertThat(lottos).isEmpty();
    }

    @Test
    void autoGeneratorFailsWhenCountIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new AutoLottosGenerator(-1));
    }

    @Test
    void manualGeneratorReturnsMarkedLottos() {
        LottoForm form = new LottoForm();
        form.mark(List.of(1, 2, 3, 4, 5, 6));
        form.mark(List.of(11, 12, 13, 14, 15, 16));

        LottosGenerator generator = new ManualLottosGenerator(form);
        List<Lotto> lottos = generator.generate();

        assertThat(lottos).hasSize(2);
        assertThat(lottos).contains(new Lotto(List.of(1, 2, 3, 4, 5, 6)));
        assertThat(lottos).contains(new Lotto(List.of(11, 12, 13, 14, 15, 16)));
    }

    @Test
    void manualGeneratorReturnsEmptyWhenNoMarks() {
        LottoForm form = new LottoForm();
        LottosGenerator generator = new ManualLottosGenerator(form);
        List<Lotto> lottos = generator.generate();
        assertThat(lottos).isEmpty();
    }

    @Test
    void compositeGeneratorCombinesResults() {
        LottoForm form = new LottoForm();
        form.mark(List.of(1, 2, 3, 4, 5, 6));

        LottosGenerator composite = new CompositeLottosGenerator(
                new ManualLottosGenerator(form),
                new AutoLottosGenerator(3)
        );

        List<Lotto> lottos = composite.generate();
        assertThat(lottos).hasSize(4);
        assertThat(lottos.getFirst()).isEqualTo(new Lotto(List.of(1, 2, 3, 4, 5, 6)));
    }

    @Test
    void compositeGeneratorWithOnlyAuto() {
        LottosGenerator composite = new CompositeLottosGenerator(
                new AutoLottosGenerator(5)
        );

        List<Lotto> lottos = composite.generate();
        assertThat(lottos).hasSize(5);
    }

    @Test
    void compositeGeneratorWithOnlyManual() {
        LottoForm form = new LottoForm();
        form.mark(List.of(1, 2, 3, 4, 5, 6));
        form.mark(List.of(7, 8, 9, 10, 11, 12));

        LottosGenerator composite = new CompositeLottosGenerator(
                new ManualLottosGenerator(form)
        );

        List<Lotto> lottos = composite.generate();
        assertThat(lottos).hasSize(2);
    }
}
