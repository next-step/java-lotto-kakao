package lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoAnswerTest {

	@Test
	@DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다")
	void create_fail_when_bonus_is_in_winning_numbers() {
		LottoTicket winningTicket = new LottoTicket(lottoNumbers(1, 2, 3, 4, 5, 6));
		assertThatThrownBy(() -> new LottoAnswer(winningTicket, LottoNumber.from(6)))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("6개 일치하면 1등이다")
	void judge_first_rank() {
		LottoTicket winningTicket = new LottoTicket(lottoNumbers(1, 2, 3, 4, 5, 6));
		LottoAnswer lottoAnswer = new LottoAnswer(winningTicket, LottoNumber.from(7));
		LottoTicket ticket = new LottoTicket(lottoNumbers(1, 2, 3, 4, 5, 6));
		assertThat(lottoAnswer.judge(ticket)).isEqualTo(Rank.FIRST);
	}

	@Test
	@DisplayName("5개 일치 + 보너스 일치면 2등이다")
	void judge_second_rank() {
		LottoTicket winningTicket = new LottoTicket(lottoNumbers(1, 2, 3, 4, 5, 6));
		LottoAnswer lottoAnswer = new LottoAnswer(winningTicket, LottoNumber.from(7));
		LottoTicket ticket = new LottoTicket(lottoNumbers(1, 2, 3, 4, 5, 7));
		assertThat(lottoAnswer.judge(ticket)).isEqualTo(Rank.SECOND);
	}

	@Test
	@DisplayName("5개 일치 + 보너스 불일치면 3등이다")
	void judge_third_rank() {
		LottoTicket winningTicket = new LottoTicket(lottoNumbers(1, 2, 3, 4, 5, 6));
		LottoAnswer lottoAnswer = new LottoAnswer(winningTicket, LottoNumber.from(7));
		LottoTicket ticket = new LottoTicket(lottoNumbers(1, 2, 3, 4, 5, 8));
		assertThat(lottoAnswer.judge(ticket)).isEqualTo(Rank.THIRD);
	}

	@Test
	@DisplayName("4개 일치하면 4등이다")
	void judge_fourth_rank() {
		LottoTicket winningTicket = new LottoTicket(lottoNumbers(1, 2, 3, 4, 5, 6));
		LottoAnswer lottoAnswer = new LottoAnswer(winningTicket, LottoNumber.from(7));
		LottoTicket ticket = new LottoTicket(lottoNumbers(1, 2, 3, 4, 8, 9));
		assertThat(lottoAnswer.judge(ticket)).isEqualTo(Rank.FOURTH);
	}

	@Test
	@DisplayName("3개 일치하면 5등이다")
	void judge_fifth_rank() {
		LottoTicket winningTicket = new LottoTicket(lottoNumbers(1, 2, 3, 4, 5, 6));
		LottoAnswer lottoAnswer = new LottoAnswer(winningTicket, LottoNumber.from(7));
		LottoTicket ticket = new LottoTicket(lottoNumbers(1, 2, 3, 8, 9, 10));
		assertThat(lottoAnswer.judge(ticket)).isEqualTo(Rank.FIFTH);
	}

	@Test
	@DisplayName("3개 미만 일치면 당첨이 아니다")
	void judge_not_winning() {
		LottoTicket winningTicket = new LottoTicket(lottoNumbers(1, 2, 3, 4, 5, 6));
		LottoAnswer lottoAnswer = new LottoAnswer(winningTicket, LottoNumber.from(7));
		LottoTicket ticket = new LottoTicket(lottoNumbers(1, 2, 8, 9, 10, 11));
		assertThat(lottoAnswer.judge(ticket)).isEqualTo(Rank.OTHER);
	}

	private ArrayList<LottoNumber> lottoNumbers(int... values) {
		ArrayList<LottoNumber> lottoNumbers = new ArrayList<>();
		for (int value : Arrays.stream(values).boxed().toList()) {
			lottoNumbers.add(LottoNumber.from(value));
		}
		return lottoNumbers;
	}
}
