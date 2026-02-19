package lotto.generator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RandomNumberGeneratorTest {

    @Test
    @DisplayName("성공 케이스")
    void success() {
        NumberGenerator module = new RandomNumberGenerator();
        List<Integer> numbers = module.generate();
        Assertions.assertThat(numbers).hasSize(6);
    }
}
