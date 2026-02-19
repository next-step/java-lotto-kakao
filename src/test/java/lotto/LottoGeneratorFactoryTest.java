package lotto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoGeneratorFactoryTest {
    @Test
    void createSuccess() {
        long totalCount = 5;
        LottoForm form = new LottoForm();
        form.mark(List.of(1, 2, 3, 4, 5, 6));

        LottosGenerator generator = LottoGeneratorFactory.create(totalCount, form);
        List<Lotto> result = generator.generate();

        assertThat(result).hasSize(5);
    }

    @Test
    void createFailByCountOver() {
        long totalCount = 1;
        LottoForm form = new LottoForm();
        form.mark(List.of(1, 2, 3, 4, 5, 6));
        form.mark(List.of(7, 8, 9, 10, 11, 12));

        assertThrows(IllegalArgumentException.class, () -> LottoGeneratorFactory.create(totalCount, form));
    }
}
