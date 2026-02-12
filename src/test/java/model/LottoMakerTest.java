package model;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class LottoMakerTest {

    @Test
    void ticketKeyEqual() {
        Lotto lotto1 = new Lotto(1, 2, 3, 4, 5, 6);
        Lotto lotto2 = new Lotto(6, 5, 4, 3, 2, 1);
        assertThat(lotto1.getKey()).isEqualTo(lotto2.getKey());
    }

    @Test
    void ticketBoothValidatorThrow() {
        List<String> prices = new ArrayList<>(Arrays.asList("1050",  "-123142341", "0"));
        for (String price : prices) {
            assertThatThrownBy(() -> new Lottos(price))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test
    void ticketBoothIssue() {
        Lottos ticketBooth = new Lottos("12000");
        List<Lotto> lottos = ticketBooth.getTickets();
        assertThat(lottos.size()).isEqualTo(12);
    }
}
