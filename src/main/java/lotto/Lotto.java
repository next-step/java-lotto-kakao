package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lotto.enums.LottoStatus;

public class Lotto {
	private static final List<Integer> ALL_NUMBERS = new ArrayList<>();

	static {
		for (int i = 1; i <= 45; i++)
			ALL_NUMBERS.add(i);
	}

	private List<Ball> balls;
	private LottoStatus status;
	private Ball bonus;

	public Lotto() {
		Collections.shuffle(ALL_NUMBERS);
		balls = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			balls.add(new Ball(ALL_NUMBERS.get(i)));
		}
		Collections.sort(balls);
		status = LottoStatus.ZERO;
		bonus = new Ball();
	}

	public Lotto(List<Ball> balls, Ball bonus) {
		Set<Ball> set = new HashSet<Ball>(balls);
		set.add(bonus);
		if (set.size() != 7) {
			throw new IllegalArgumentException("로또 번호는 1 ~ 45 사이 6개의 서로다른 수와 1개의 보너스 공으로 입력해야 합니다.");
		}
		Collections.sort(balls);
		this.balls = balls;
		this.status = LottoStatus.ANSWER;
		this.bonus = bonus;
	}

	public void check(Lotto answer) {
		List<Ball> answerBalls = answer.getBalls();
		for (Ball ball : this.balls) {
			updateStatus(answerBalls, ball, false);
		}
		if (status == LottoStatus.FIVE) {
			updateStatus(this.balls, answer.getBonus(), true);
		}
	}

	private void updateStatus(List<Ball> balls, Ball ball, boolean isBonus) {
		if (balls.contains(ball)) {
			this.status = isBonus ? LottoStatus.SIX_BONUS : LottoStatus.update(this.status);
		}
	}

	public List<Ball> getBalls() {
		return balls;
	}

	public LottoStatus getStatus() {
		return status;
	}

	public void setStatus(LottoStatus status) {
		this.status = status;
	}

	public Ball getBonus() {
		return bonus;
	}

	@Override
	public String toString() {
		return balls.toString();
	}
}
