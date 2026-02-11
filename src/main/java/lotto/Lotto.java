package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lotto.enums.LottoStatus;

public class Lotto {
	private static final int LOTTO_SIZE = 6;
	private static final int MIN_NUMBER = 1;
	private static final int MAX_NUMBER = 45;

	private static final List<Integer> ALL_NUMBERS =
		java.util.stream.IntStream.rangeClosed(MIN_NUMBER, MAX_NUMBER)
			.boxed()
			.toList();
	private List<Ball> balls;
	private LottoStatus status;
	private Ball bonus;

	public Lotto() {
		List<Integer> numbers = new ArrayList<>(ALL_NUMBERS);
		Collections.shuffle(numbers);

		balls = new ArrayList<>();
		for (int i = 0; i < LOTTO_SIZE; i++) {
			balls.add(new Ball(numbers.get(i)));
		}
		Collections.sort(balls);
		status = LottoStatus.ZERO;
		bonus = new Ball();
	}

	public Lotto(List<Ball> balls, Ball bonus) {
		Set<Ball> set = new HashSet<Ball>(balls);
		set.add(bonus);
		if (set.size() != LOTTO_SIZE + 1) {
			throw new IllegalArgumentException("중복된 숫자 입력은 불가합니다.");
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
