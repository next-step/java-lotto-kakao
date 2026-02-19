package lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import lotto.enums.LottoStatus;

public class LottoTest {
	@Test
	void makeUserLottoAuto() {
		Lotto lotto = new Lotto();

		Set<Ball> set = new HashSet<Ball>(lotto.getBalls());
		assertThat(set.size()).isEqualTo(6);
		assertThat(lotto.getStatus()).isEqualTo(LottoStatus.ZERO);
		assertThat(lotto.getBonus()).isEqualTo(new Ball());
	}

	@Test
	void makeUserLottoManual() {
		List<Ball> balls = new ArrayList<>(List.of(new Ball(1), new Ball(2), new Ball(3)
			, new Ball(4), new Ball(5), new Ball(6)));
		Lotto lotto = new Lotto(balls);

		Set<Ball> set = new HashSet<Ball>(lotto.getBalls());
		assertThat(set.size()).isEqualTo(6);
		assertThat(lotto.getStatus()).isEqualTo(LottoStatus.ZERO);
		assertThat(lotto.getBonus()).isEqualTo(new Ball());
	}

	@Test
	void makeAnswerLotto() {
		List<Ball> answer = new ArrayList<Ball>(List.of(new Ball(1), new Ball(2), new Ball(3),
			new Ball(4), new Ball(5), new Ball(6)));
		Ball bonus = new Ball(7);
		Lotto lotto = new Lotto(answer, bonus);

		Set<Ball> set = new HashSet<Ball>(lotto.getBalls());
		assertThat(set.size()).isEqualTo(6);
		assertThat(lotto.getStatus()).isEqualTo(LottoStatus.ANSWER);
		assertThat(lotto.getBonus()).isEqualTo(bonus);
	}

	@Test
	void 당첨번호_개수가_적은경우() {
		List<Ball> answer = new ArrayList<Ball>(List.of(new Ball(2), new Ball(3),
			new Ball(4), new Ball(5), new Ball(6)));
		Ball bonus = new Ball(1);
		assertThatIllegalArgumentException().isThrownBy(() -> new Lotto(answer, bonus));
	}

	@Test
	void 당첨번호가_중복된_경우() {
		List<Ball> answer = new ArrayList<Ball>(List.of(new Ball(1), new Ball(2), new Ball(3),
			new Ball(4), new Ball(5), new Ball(5)));
		Ball bonus = new Ball(7);
		assertThatIllegalArgumentException().isThrownBy(() -> new Lotto(answer, bonus));
	}

	@Test
	void 보너스와_당첨번호가_중복이되면_안된다() {
		List<Ball> answer = new ArrayList<Ball>(List.of(new Ball(1), new Ball(2), new Ball(3),
			new Ball(4), new Ball(5), new Ball(6)));
		Ball bonus = new Ball(1);
		assertThatIllegalArgumentException().isThrownBy(() -> new Lotto(answer, bonus));
	}

	@Test
	void 로또를_3개_맞췄을떄_정답의_비교결과가_일치하는지() {
		List<Ball> answer = new ArrayList<Ball>(List.of(new Ball(1), new Ball(2), new Ball(3),
			new Ball(4), new Ball(5), new Ball(6)));
		Ball bonus = new Ball(7);
		Lotto answerLotto = new Lotto(answer, bonus);

		List<Ball> userBalls = new ArrayList<Ball>(List.of(new Ball(1), new Ball(2), new Ball(3),
			new Ball(7), new Ball(8), new Ball(9)));
		Ball userBonus = new Ball();
		Lotto userLotto = new Lotto(userBalls, userBonus);
		userLotto.setStatus(LottoStatus.ZERO);

		userLotto.check(answerLotto);
		assertThat(userLotto.getStatus()).isEqualTo(LottoStatus.THREE);

	}

	@Test
	void 로또를_5개_맞췄을때_보너스여부르_확인() {
		List<Ball> answer = new ArrayList<Ball>(List.of(new Ball(1), new Ball(2), new Ball(3),
			new Ball(4), new Ball(5), new Ball(6)));
		Ball bonus = new Ball(7);
		Lotto answerLotto = new Lotto(answer, bonus);

		List<Ball> userBalls = new ArrayList<Ball>(List.of(new Ball(1), new Ball(2), new Ball(3),
			new Ball(4), new Ball(5), new Ball(7)));
		Ball userBonus = new Ball();
		Lotto userLotto = new Lotto(userBalls, userBonus);
		userLotto.setStatus(LottoStatus.ZERO);

		userLotto.check(answerLotto);
		assertThat(userLotto.getStatus()).isEqualTo(LottoStatus.SIX_BONUS);

	}
}
