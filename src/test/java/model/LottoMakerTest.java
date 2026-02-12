package model;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class LottoMakerTest {

    @Test
    void ticketKeyEqual() {
        LottoNumber number1 = new LottoNumber(1);
        LottoNumber number2 = new LottoNumber(2);
        LottoNumber number3 = new LottoNumber(3);
        LottoNumber number4 = new LottoNumber(4);
        LottoNumber number5 = new LottoNumber(5);
        LottoNumber number6 = new LottoNumber(6);
        Lotto lotto1 = new Lotto(new LottoNumbers(
                Arrays.asList(number1, number2, number3, number4, number5, number6)
        ));
        Lotto lotto2 = new Lotto(new LottoNumbers(
                Arrays.asList(number6, number5, number4, number3, number2, number1)
        ));
        assertThat(lotto1.getNumberMask()).isEqualTo(lotto2.getNumberMask());
    }

    @Test
    void ticketBoothValidatorThrow() {
        List<Integer> prices = Arrays.asList(1050, -123142341, 0);
        for (int price : prices) {
            assertThatThrownBy(() -> new Lottos(price))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test
    void ticketBoothIssue() {
        Lottos ticketBooth = new Lottos(12000);
        List<Lotto> lottos = ticketBooth.getLottos();
        assertThat(lottos.size()).isEqualTo(12);
    }
}
