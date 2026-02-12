package lotto;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AnswerLotto {
	private static final int LOTTO_SIZE = 6;

	private final Set<Ball> balls;
	private final Ball bonus;

	public AnswerLotto(List<Ball> balls, Ball bonus) {
		Set<Ball> set = new HashSet<>(balls);
		if (set.size() != LOTTO_SIZE) {
			throw new IllegalArgumentException("정답 번호는 중복 없이 6개여야 합니다.");
		}
		if (set.contains(bonus)) {
			throw new IllegalArgumentException("보너스 번호는 정답 번호와 중복될 수 없습니다.");
		}
		this.balls = Collections.unmodifiableSet(set);
		this.bonus = bonus;
	}

	public Set<Ball> getBalls() {
		return balls;
	}

	public Ball getBonus() {
		return bonus;
	}
}
