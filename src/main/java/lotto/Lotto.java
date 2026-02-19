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

	public Lotto(List<Ball> balls, LottoStatus status, Ball bonus) {
		Set<Ball> set = new HashSet<Ball>(balls);
		set.add(bonus);
		if (set.size() != 7) {
			throw new IllegalArgumentException("로또 번호는 1 ~ 45 사이 6개의 서로다른 수와 1개의 보너스 공으로 입력해야 합니다.");
		}
		Collections.sort(balls);

		this.balls = balls;
		this.status = status;
		this.bonus = bonus;
	}

	public Lotto() {
		this(createRandomBalls());
	}

	public Lotto(List<Ball> balls) {
		this(balls, LottoStatus.ZERO, new Ball());
	}

	public Lotto(List<Ball> balls, Ball bonus) {
		this(balls, LottoStatus.ANSWER, bonus);
	}

	private static List<Ball> createRandomBalls() {
		Collections.shuffle(ALL_NUMBERS);
		List<Ball> randomBalls = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			randomBalls.add(new Ball(ALL_NUMBERS.get(i)));
		}
		return randomBalls;
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
