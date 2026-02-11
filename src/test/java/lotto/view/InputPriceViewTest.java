package lotto.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InputPriceViewTest {
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
    @DisplayName("구입 금액은 숫자만 허용되고, 유효 값이 입력될 때까지 다시 입력 받는다.")
    public void test_input_price_number_only() {
        setInput("abc\n1000\n");

        InputPriceView inputView = new InputPriceView(1000);
        int price = inputView.inputPrice();

        assertEquals(1000, price);
    }

    @Test
    @DisplayName("구입 금액은 1000원 이상이어야 하고, 범위를 벗어나면 다시 입력 받는다.")
    public void test_input_price_minimum() {
        setInput("999\n1000\n");

        InputPriceView inputView = new InputPriceView(1000);
        int price = inputView.inputPrice();

        assertEquals(1000, price);
    }
}
