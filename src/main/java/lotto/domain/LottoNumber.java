package lotto.domain;

import static lotto.domain.LottoPolicy.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class LottoNumber implements Comparable<LottoNumber> {
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
		boolean inRange = value >= MIN_LOTTO_NUMBER && value <= MAX_LOTTO_NUMBER;
		if (!inRange) {
			throw new IllegalArgumentException(
				String.format("로또 번호는 %d부터 %d까지여야 합니다.", MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER));
		}
	}

	private static Map<Integer, LottoNumber> createPool() {
		Map<Integer, LottoNumber> cache = new LinkedHashMap<>();
		for (int number = MIN_LOTTO_NUMBER; number <= MAX_LOTTO_NUMBER; number++) {
			cache.put(number, new LottoNumber(number));
		}
		return Collections.unmodifiableMap(cache);
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
