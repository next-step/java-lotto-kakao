package lotto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoFormTest {
    private LottoForm lottoForm;

    @BeforeEach
    void setUp() {
        lottoForm = new LottoForm();
    }

    @Test
    void markSuccess() {
        List<Integer> numbers1 = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> numbers2 = List.of(1, 11, 12, 13, 14, 45);
        assertDoesNotThrow(() -> lottoForm.mark(numbers1));
        assertDoesNotThrow(() -> lottoForm.mark(numbers2));
        assertThat(lottoForm.manualLottos().size()).isEqualTo(2);
        assertThat(lottoForm.manualLottos().getFirst().equals(new Lotto(numbers1))).isEqualTo(true);
        assertThat(lottoForm.manualLottos().get(1).equals(new Lotto(numbers2))).isEqualTo(true);
    }

    @Test
    void markFailInvalidNumberCount() {
        assertThrows(IllegalArgumentException.class,
                () -> lottoForm.mark(List.of(1, 2, 3, 4, 5)));
        assertThrows(IllegalArgumentException.class,
                () -> lottoForm.mark(List.of(1, 2, 3, 4, 5, 6, 7)));
    }

    @Test
    void markFailDuplicatedNumber() {
        assertThrows(IllegalArgumentException.class,
                () -> lottoForm.mark(List.of(1, 2, 3, 4, 5, 5)));
    }

    @Test
    void markFailInvalidNumberRange() {
        assertThrows(IllegalArgumentException.class,
                () -> lottoForm.mark(List.of(1, 2, 3, 4, 5, 46)));
        assertThrows(IllegalArgumentException.class,
                () -> lottoForm.mark(List.of(0, 1, 2, 3, 4, 5)));
    }

    @Test
    void markFailMarkedNull() {
        assertThrows(NullPointerException.class,
                () -> lottoForm.mark(null));
    }
}
