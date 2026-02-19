package lotto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CompositeLottosGeneratorTest {

    @Test
    void ManualAndAutoGenerators() {
        Lotto manual = new Lotto(8, 21, 23, 41, 42, 43);
        Lotto auto = new Lotto(1, 2, 3, 4, 5, 6);
        LottoGenerator fixedGenerator = new LottoGenerator() {
            @Override
            public Lotto generate() {
                return auto;
            }
        };

        LottosGenerator lottosGenerator = new CompositeLottosGenerator(List.of(
                new ManualLottosGenerator(List.of(manual)),
                new AutoLottosGenerator(2L, fixedGenerator)
        ));

        LottoBundle lottoBundle = lottosGenerator.generate();

        assertThat(lottoBundle.size()).isEqualTo(3);
        assertThat(lottoBundle.asList()).isEqualTo(List.of(manual, auto, auto));
    }

    @Test
    void ChildrenContainNull() {
        List<LottosGenerator> children = new ArrayList<>();
        children.add(null);

        assertThrows(IllegalArgumentException.class,
                () -> new CompositeLottosGenerator(children));
    }
}
