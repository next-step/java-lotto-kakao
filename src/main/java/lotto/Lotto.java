package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import lotto.enums.LottoStatus;

public class Lotto {
	private static final int LOTTO_SIZE = 6;
	private static final int MIN_NUMBER = 1;
	private static final int MAX_NUMBER = 45;

	private static final List<Integer> ALL_NUMBERS =
		IntStream.rangeClosed(MIN_NUMBER, MAX_NUMBER).boxed().toList();

	private final Set<Ball> balls;

	public Lotto() {
		List<Integer> numbers = new ArrayList<>(ALL_NUMBERS);
		Collections.shuffle(numbers);

		Set<Ball> temp = new HashSet<>();
		for (int i = 0; i < LOTTO_SIZE; i++) {
			temp.add(new Ball(numbers.get(i)));
		}
		this.balls = Collections.unmodifiableSet(temp);
	}

	public Lotto(List<Ball> balls) {
		if (new HashSet<>(balls).size() != LOTTO_SIZE) {
			throw new IllegalArgumentException("로또 번호는 중복 없이 6개여야 합니다.");
		}
		this.balls = Collections.unmodifiableSet(new HashSet<>(balls));
	}

	public LottoStatus check(AnswerLotto answer) {
		Set<Ball> answerBalls = answer.getBalls();
		int matchingCount = 0;

		for (Ball n : balls) {
			if (answerBalls.contains(n))
				matchingCount++;
		}

		boolean hasBonus = matchingCount == 5 && balls.contains(answer.getBonus());
		return LottoStatus.of(matchingCount, hasBonus);
	}

	public Set<Ball> getBalls() {
		return balls;
	}

	@Override
	public String toString() {
		return balls.toString();
	}
}
