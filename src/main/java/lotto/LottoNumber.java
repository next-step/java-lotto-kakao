package lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoNumber implements Comparable<LottoNumber>{

	private static final int MIN = 1;
	private static final int MAX = 45;

	private static final List<LottoNumber> CACHE = new ArrayList<>();

	static {
		for (int i = MIN; i <= MAX; i++) {
			CACHE.add(new LottoNumber(i));
		}
	}

	private final int number;

	LottoNumber(int number) {
		this.number = number;
	}

	public static List<LottoNumber> getCache() {
		return new ArrayList<>(CACHE);
	}

	public int getNumber() {
		return number;
	}

	@Override
	public int compareTo(LottoNumber that) {
		return this.number - that.number;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof LottoNumber that)) {
			return false;
		}
		return number == that.number;
	}

	@Override
	public String toString() {
		return String.valueOf(number);
	}

}

