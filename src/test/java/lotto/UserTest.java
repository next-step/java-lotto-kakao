package lotto;

import lotto.enums.LottoStatus;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class UserTest {
    @Test
    void 유저_구입금액은_1000으로_나눠떨어져야_한다() {
        assertThatIllegalArgumentException().isThrownBy(() ->new User("1001"));
    }

    @Test
    void 유저_구입금액은_1000으로_나눈_몫만큼의_개수로_로또를_구입해야_한다() {
        String price = "10000";
        User user = new User(price);

        assertThat(user.getLottos().size()).isEqualTo(Long.parseLong(price) / 1000L);
    }

    @Test
    void 유저가_얻은_상금을_계산한다() {
        String price = "2000";
        User user = new User(price);
        List<Lotto> lottos = new ArrayList<>();

        List<Ball> userBalls = new ArrayList<Ball>(List.of(new Ball(1), new Ball(2), new Ball(3),
                new Ball(4), new Ball(5), new Ball(6)));
        Ball userBonus = new Ball();
        Lotto userLotto = new Lotto(userBalls,userBonus);
        userLotto.setStatus(LottoStatus.ZERO);

        List<Ball> userBalls2 = new ArrayList<Ball>(List.of(new Ball(1), new Ball(2), new Ball(3),
                new Ball(4), new Ball(5), new Ball(7)));
        Ball userBonus2 = new Ball();
        Lotto userLotto2 = new Lotto(userBalls2,userBonus2);
        userLotto2.setStatus(LottoStatus.ZERO);
        lottos.add(userLotto);
        lottos.add(userLotto2);
        user.setLottos(lottos);

        List<Ball> answer = new ArrayList<Ball>(List.of(new Ball(1), new Ball(2), new Ball(3),
                new Ball(4), new Ball(5), new Ball(6)));
        Ball bonus = new Ball(7);
        Lotto answerLotto = new Lotto(answer,bonus);

        user.calculateAward(answerLotto);

        assertThat(user.getAward()).isEqualTo(LottoStatus.SIX.getMoney() + LottoStatus.SIX_BONUS.getMoney());
        assertThat(user.getResult().get(LottoStatus.SIX)).isEqualTo(1);
        assertThat(user.getResult().get(LottoStatus.SIX_BONUS)).isEqualTo(1);
    }
}
