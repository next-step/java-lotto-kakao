package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lotto.exception.LottoValidationException;

@Getter
@EqualsAndHashCode
public class LottoNumber implements Comparable<LottoNumber> {
	private static final int MIN_NUMBER = 1;
	private static final int MAX_NUMBER = 45;
	private static final Map<Integer, LottoNumber> POOL = createPool();

	private final int value;

	private LottoNumber(int value) {
		validate(value);
		this.value = value;
	}

	public static LottoNumber from(int value) {
		validate(value);
		return POOL.get(value);
	}

	public static List<LottoNumber> getPool() {
		return new ArrayList<>(POOL.values());
	}

	private static void validate(int value) {
		boolean inRange = value >= MIN_NUMBER && value <= MAX_NUMBER;
		if (!inRange) {
			throw new LottoValidationException(
				String.format("로또 번호는 %d부터 %d 사이여야 합니다.", MIN_NUMBER, MAX_NUMBER));
		}
	}

	private static Map<Integer, LottoNumber> createPool() {
		Map<Integer, LottoNumber> pool = new LinkedHashMap<>();
		for (int number = MIN_NUMBER; number <= MAX_NUMBER; number++) {
			pool.put(number, new LottoNumber(number));
		}
		return Collections.unmodifiableMap(pool);
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
