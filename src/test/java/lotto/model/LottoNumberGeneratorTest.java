package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoNumberGeneratorTest {

    @Test
    @DisplayName("로또 번호 생성기는 0개 이상의 로또 번호를 생성한다.")
    public void test_generate_count() {
        LottoNumberGenerator generator = new LottoNumberGenerator();

        assertThrows(IllegalArgumentException.class, () ->
            generator.generate(-1)
        );
        assertDoesNotThrow(() ->
            generator.generate(0)
        );
    }

    @Test
    @DisplayName("로또 번호 생성기는 로또 당 6개의 숫자를 생성한다.")
    public void test_generate_numbers() {
        LottoNumberGenerator generator = new LottoNumberGenerator();
        List<LottoNumbers> numbers = generator.generate(1);

        assertEquals(6, numbers.getFirst().getNumbers().size());
    }

    @Test
    @DisplayName("로또 번호 생성기는 1 ~ 45 범위의 중복 없는 6개 숫자를 생성한다.")
    public void test_generate_single() {
        LottoNumberGenerator generator = new LottoNumberGenerator();
        LottoNumbers lotto = generator.generate();
        List<Integer> numbers = lotto.getNumbers();

        assertEquals(6, numbers.size());
        assertEquals(6, new HashSet<>(numbers).size());
        for (Integer number : numbers) {
            assertTrue(number >= 1 && number <= 45);
        }
    }

    @Test
    @DisplayName("로또 번호 생성기는 입력된 개수만큼 로또 번호를 생성한다.")
    public void test_generate_multiple() {
        LottoNumberGenerator generator = new LottoNumberGenerator();
        List<LottoNumbers> lotto0 = generator.generate(0);
        List<LottoNumbers> lotto5 = generator.generate(5);

        assertEquals(0, lotto0.size());
        assertEquals(5, lotto5.size());
    }

    @Test
    @DisplayName("로또 번호 생성기의 seed를 지정하면 결과가 재현 가능해야 한다.")
    public void test_generate_reproducible() {
        LottoNumberGenerator firstGenerator = new LottoNumberGenerator(0L);
        LottoNumberGenerator secondGenerator = new LottoNumberGenerator(0L);

        for (int index = 0; index < 3; index++) {
            List<Integer> firstNumbers = firstGenerator.generate().getNumbers();
            List<Integer> secondNumbers = secondGenerator.generate().getNumbers();

            assertEquals(firstNumbers, secondNumbers);
            assertEquals(6, firstNumbers.size());
            assertEquals(6, new HashSet<>(firstNumbers).size());
            for (Integer number : firstNumbers) {
                assertTrue(number >= 1 && number <= 45);
            }
        }
    }
}
