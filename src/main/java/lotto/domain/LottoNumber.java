package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lotto.exception.LottoValidationException;

@Getter
@EqualsAndHashCode
public class LottoNumber implements Comparable<LottoNumber> {
	private static final int MIN_NUMBER = 1;
	private static final int MAX_NUMBER = 45;
	private static final List<LottoNumber> POOL = createPool();

	private final int value;

	private LottoNumber(int value) {
		validate(value);
		this.value = value;
	}

	public static LottoNumber from(int value) {
		validate(value);
		return POOL.get(value - 1);
	}

	public static List<LottoNumber> getPool() {
		return new ArrayList<>(POOL);
	}

	private static void validate(int value) {
		boolean inRange = value >= MIN_NUMBER && value <= MAX_NUMBER;
		if (!inRange) {
			throw new LottoValidationException(
				String.format("로또 번호는 %d부터 %d 사이여야 합니다.", MIN_NUMBER, MAX_NUMBER));
		}
	}

	private static List<LottoNumber> createPool() {
		List<LottoNumber> pool = new ArrayList<>();
		for (int number = MIN_NUMBER; number <= MAX_NUMBER; number++) {
			pool.add(new LottoNumber(number));
		}
		return Collections.unmodifiableList(pool);
	}

	@Override
	public int compareTo(LottoNumber other) {
		return Integer.compare(this.value, other.value);
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}
}
