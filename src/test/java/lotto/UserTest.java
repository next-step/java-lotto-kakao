package lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import lotto.enums.LottoStatus;

public class UserTest {

	@Test
	void 유저_구입금액은_1000으로_나눈_몫만큼의_개수로_로또를_구입해야_한다() {
		Money money = new Money("5000");
		LottoList lottos = new AutoLottoGenerator(5).generate();
		User user = new User(money, lottos);

		assertThat(user.getLottos().size()).isEqualTo(money.getPrice() / 1000L);
	}

	@Test
	void 유저가_얻은_상금을_계산한다() {
		Money money = new Money("2000");
		LottoList lottoList = new LottoList();
		User user = new User(money, lottoList);

		List<Lotto> lottos = new ArrayList<>();
		lottos.add(new Lotto(List.of(
			new Ball(1), new Ball(2), new Ball(3),
			new Ball(4), new Ball(5), new Ball(6) // 1등
		)));
		lottos.add(new Lotto(List.of(
			new Ball(1), new Ball(2), new Ball(3),
			new Ball(4), new Ball(5), new Ball(7) // 5개+보너스(2등) 되려면 정답이 1~6 + bonus 7이어야 함
		)));
		user.setLottos(lottos);

		AnswerLotto answerLotto = new AnswerLotto(
			List.of(new Ball(1), new Ball(2), new Ball(3),
				new Ball(4), new Ball(5), new Ball(6)),
			new Ball(7)
		);

		user.calculateAward(answerLotto);

		assertThat(user.getAward()).isEqualTo(
			LottoStatus.SIX.getMoney() + LottoStatus.SIX_BONUS.getMoney()
		);
		assertThat(user.getResult().get(LottoStatus.SIX)).isEqualTo(1);
		assertThat(user.getResult().get(LottoStatus.SIX_BONUS)).isEqualTo(1);
	}
}
