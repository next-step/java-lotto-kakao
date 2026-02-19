package lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import lotto.enums.LottoStatus;

public class UserTest {

	@Test
	void 유저가_얻은_상금을_계산한다() {
		String price = "2000";
		Lottos lottos = new Lottos();

		List<Ball> userBalls = new ArrayList<Ball>(List.of(new Ball(1), new Ball(2), new Ball(3),
			new Ball(4), new Ball(5), new Ball(6)));
		Lotto userLotto = new Lotto(userBalls);

		List<Ball> userBalls2 = new ArrayList<Ball>(List.of(new Ball(1), new Ball(2), new Ball(3),
			new Ball(4), new Ball(5), new Ball(7)));
		Lotto userLotto2 = new Lotto(userBalls2);

		lottos.add(userLotto);
		lottos.add(userLotto2);
		User user = new User(price, lottos);

		List<Ball> answer = new ArrayList<Ball>(List.of(new Ball(1), new Ball(2), new Ball(3),
			new Ball(4), new Ball(5), new Ball(6)));
		Ball bonus = new Ball(7);
		Lotto answerLotto = new Lotto(answer, bonus);

		user.calculateAward(answerLotto);

		assertThat(user.getAward()).isEqualTo(LottoStatus.SIX.getMoney() + LottoStatus.SIX_BONUS.getMoney());
		assertThat(user.getResult().get(LottoStatus.SIX)).isEqualTo(1);
		assertThat(user.getResult().get(LottoStatus.SIX_BONUS)).isEqualTo(1);
	}

	@Test
	void 가진돈보다_많은_수동을_구매하려는_경우() {
		String price = "2000";
		final int manualCount = 4;
		Lottos lottos = new Lottos();

		for (int i = 0; i < manualCount; i++) {
			List<Ball> userBalls = new ArrayList<Ball>(List.of(new Ball(1), new Ball(2), new Ball(3),
				new Ball(4), new Ball(5), new Ball(7)));
			Lotto userLotto2 = new Lotto(userBalls);
			lottos.add(userLotto2);
		}

		assertThatIllegalArgumentException().isThrownBy(() -> new User(price, lottos));
	}

	@Test
	void 남은_금액을_자동으로_채우는지_테스트() {
		String price = "5000";
		final int manualCount = 3;
		Lottos lottos = new Lottos();

		for (int i = 0; i < manualCount; i++) {
			List<Ball> userBalls = new ArrayList<Ball>(List.of(new Ball(1), new Ball(2), new Ball(3),
				new Ball(4), new Ball(5), new Ball(7)));
			Lotto userLotto2 = new Lotto(userBalls);
			lottos.add(userLotto2);
		}
		User user = new User(price, lottos);

		assertThat(user.getLottos().size()).isEqualTo(5);
	}

	@Test
	void 모두_자동으로_생성되는지_테스트() {
		String price = "5000";
		Lottos lottos = new Lottos();
		User user = new User(price, lottos);
		assertThat(user.getLottos().size()).isEqualTo(5);
	}
}
