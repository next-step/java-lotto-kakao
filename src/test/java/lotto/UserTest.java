package lotto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class UserTest {
    @Test
    void 유저_구입금액은_1000으로_나눠떨어져야_한다() {
        assertThatIllegalArgumentException().isThrownBy(() ->new User(1001));
    }

    @Test
    void 유저_구입금액은_1000으로_나눈_몫만큼의_개수로_로또를_구입해야_한다() {
        long price = 10000;
        User user = new User(price);

        assertThat(user.getLottos().size()).isEqualTo(price / 1000L);
    }
}
