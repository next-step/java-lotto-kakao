package lotto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoFormTest {
    @Test
    void markSuccess() {
        LottoForm lottoForm = new LottoForm();
        lottoForm.mark(List.of(1, 2, 3, 4, 5, 6));
        lottoForm.mark(List.of(1, 11, 12, 13, 14, 45));
        LottoBundle lottoBundle = lottoForm.submit();
        assertThat(lottoBundle.size()).isEqualTo(2);
    }

    @Test
    void markFailInvalidNumberCount() {
        LottoForm lottoForm = new LottoForm();
        assertThrows(IllegalArgumentException.class,
                () -> lottoForm.mark(List.of(1, 2, 3, 4, 5)));
    }

    @Test
    void markFailDuplicatedNumber() {
        LottoForm lottoForm = new LottoForm();
        assertThrows(IllegalArgumentException.class,
                () -> lottoForm.mark(List.of(1, 2, 3, 4, 5, 5)));
    }

    @Test
    void markFailInvalidNumberRange() {
        LottoForm lottoForm = new LottoForm();
        assertThrows(IllegalArgumentException.class,
                () -> lottoForm.mark(List.of(1, 2, 3, 4, 5, 46)));
        assertThrows(IllegalArgumentException.class,
                () -> lottoForm.mark(List.of(0, 1, 2, 3, 4, 5)));
    }

    @Test
    void markFailMarkedNull() {
        LottoForm lottoForm = new LottoForm();
        assertThrows(NullPointerException.class,
                () -> lottoForm.mark(null));
    }

    @Test
    void submitFailEmptyFormSubmitted() {
        LottoForm lottoForm = new LottoForm();
        assertThrows(IllegalArgumentException.class,
                lottoForm::submit);
    }
}
