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
	void makeUserLotto() {
		Lotto lotto = new Lotto();

		Set<Ball> set = new HashSet<>(lotto.getBalls());
		assertThat(set.size()).isEqualTo(6);
	}

	@Test
	void makeWinningLotto() {
		List<Ball> answer = new ArrayList<>(List.of(
			new Ball(1), new Ball(2), new Ball(3),
			new Ball(4), new Ball(5), new Ball(6)
		));
		Ball bonus = new Ball(7);

		AnswerLotto winningLotto = new AnswerLotto(answer, bonus);

		assertThat(winningLotto.getBalls().size()).isEqualTo(6);
		assertThat(winningLotto.getBonus()).isEqualTo(bonus);
	}

	@Test
	void 당첨번호_개수가_적은경우() {
		List<Ball> answer = new ArrayList<>(List.of(
			new Ball(2), new Ball(3), new Ball(4), new Ball(5), new Ball(6)
		));
		Ball bonus = new Ball(1);

		assertThatIllegalArgumentException()
			.isThrownBy(() -> new AnswerLotto(answer, bonus));
	}

	@Test
	void 당첨번호가_중복된_경우() {
		List<Ball> answer = new ArrayList<>(List.of(
			new Ball(1), new Ball(2), new Ball(3),
			new Ball(4), new Ball(5), new Ball(5)
		));
		Ball bonus = new Ball(7);

		assertThatIllegalArgumentException()
			.isThrownBy(() -> new AnswerLotto(answer, bonus));
	}

	@Test
	void 보너스와_당첨번호가_중복이되면_안된다() {
		List<Ball> answer = new ArrayList<>(List.of(
			new Ball(1), new Ball(2), new Ball(3),
			new Ball(4), new Ball(5), new Ball(6)
		));
		Ball bonus = new Ball(1);

		assertThatIllegalArgumentException()
			.isThrownBy(() -> new AnswerLotto(answer, bonus));
	}

	@Test
	void 로또를_3개_맞췄을떄_정답의_비교결과가_일치하는지() {
		AnswerLotto answerLotto = new AnswerLotto(
			List.of(new Ball(1), new Ball(2), new Ball(3),
				new Ball(4), new Ball(5), new Ball(6)),
			new Ball(7)
		);

		Lotto userLotto = new Lotto(List.of(
			new Ball(1), new Ball(2), new Ball(3),
			new Ball(7), new Ball(8), new Ball(9)
		));

		LottoStatus result = userLotto.check(answerLotto);
		assertThat(result).isEqualTo(LottoStatus.THREE);
	}

	@Test
	void 로또를_5개_맞췄을때_보너스여부를_확인() {
		AnswerLotto answerLotto = new AnswerLotto(
			List.of(new Ball(1), new Ball(2), new Ball(3),
				new Ball(4), new Ball(5), new Ball(6)),
			new Ball(7)
		);

		Lotto userLotto = new Lotto(List.of(
			new Ball(1), new Ball(2), new Ball(3),
			new Ball(4), new Ball(5), new Ball(7) // 5개 + 보너스
		));

		LottoStatus result = userLotto.check(answerLotto);
		assertThat(result).isEqualTo(LottoStatus.SIX_BONUS);
	}
}
