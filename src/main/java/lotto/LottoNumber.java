package lotto;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LottoNumber {
	private static final int MIN_NUMBER = 1;
	private static final int MAX_NUMBER = 45;
	private static final Map<Integer, LottoNumber> CACHE = createCache();
	private final int value;

	private LottoNumber(int value) {
		this.value = value;
	}

	public static LottoNumber from(int value) {
		validateNumber(value);
		return CACHE.get(value);
	}

	private static void validateNumber(int value) {
		if (value < MIN_NUMBER || value > MAX_NUMBER) {
			throw new IllegalArgumentException("로또 번호는 1부터 45 사이여야 합니다.");
		}
	}

	private static Map<Integer, LottoNumber> createCache() {
		Map<Integer, LottoNumber> lottoNumbers = new HashMap<>();
		for (int value = MIN_NUMBER; value <= MAX_NUMBER; value++) {
			lottoNumbers.put(value, new LottoNumber(value));
		}
		return lottoNumbers;
	}

	public int getValue() {
		return value;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (!(object instanceof LottoNumber lottoNumber)) {
			return false;
		}
		return value == lottoNumber.value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}
}
