package lotto.view;

import lotto.model.LottoNumbers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InputManualViewTest {
    private InputStream originalIn;

    @BeforeEach
    void setUp() {
        originalIn = System.in;
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalIn);
    }

    private void setInput(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8)));
    }

    @Test
    @DisplayName("수동 구매 개수가 유효할 때까지 다시 입력 받는다.")
    public void test_input_manual_count_retry() {
        setInput("abc\n3\n");

        InputManualView inputView = new InputManualView();
        int manualCount = inputView.inputManualCount(5);

        assertEquals(3, manualCount);
    }

    @Test
    @DisplayName("수동 구매 개수는 최대 구매 개수를 넘을 수 없다.")
    public void test_input_manual_maximum() {
        setInput("6\n5\n");

        InputManualView inputView = new InputManualView();
        int manualCount = inputView.inputManualCount(5);

        assertEquals(5, manualCount);
    }

    @Test
    @DisplayName("수동 구매 개수가 0이면 빈 목록을 반환한다.")
    public void test_input_manual_empty() {
        InputManualView inputView = new InputManualView();
        List<LottoNumbers> list = inputView.inputManualLottos(0);

        assertEquals(0, list.size());
    }

    @Test
    @DisplayName("수동 로또 번호는 유효할 때까지 다시 입력 받는다.")
    public void test_input_manual_lotto_retry() {
        setInput("1,2,3,4,5\n1,2,3,4,5,6\n");

        InputManualView inputView = new InputManualView();
        List<LottoNumbers> list = inputView.inputManualLottos(1);

        assertEquals(1, list.size());
        assertEquals(List.of(1, 2, 3, 4, 5, 6), list.getFirst().getNumbers());
    }
}
