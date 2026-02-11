package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lotto {

	private static final int LOTTO_SIZE = 6;
	private final List<LottoNumber> numbers;

	Lotto(List<LottoNumber> numbers) {
		validate(numbers);
		this.numbers = List.copyOf(numbers);
	}

	private void validate(List<LottoNumber> numbers) {
		if (numbers.size() != LOTTO_SIZE) {
			throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
		}
		long distinctCount = numbers.stream().distinct().count();
		if (distinctCount != LOTTO_SIZE) {
			throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
		}
	}

	public static Lotto createRandomLotto() {
		List<LottoNumber> pool = LottoNumber.getCache();
		Collections.shuffle(pool);

		List<LottoNumber> picked = pool.subList(0, LOTTO_SIZE)
			.stream()
			.sorted()
			.collect(Collectors.toList());

		return new Lotto(picked);
	}

	List<LottoNumber> getNumbers() {
		return this.numbers;
	}

}
