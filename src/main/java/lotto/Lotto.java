package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lotto {

	private final List<Integer> numbers;

	Lotto(List<Integer> numbers) {
		this.numbers = List.copyOf(numbers);
	}

	public static Lotto createRandomLotto() {
		List<Integer> pool = IntStream.rangeClosed(1, 45)
			.boxed()
			.collect(Collectors.toList());

		Collections.shuffle(pool);
		List<Integer> picked = new ArrayList<>(pool.subList(0, 6));
		Collections.sort(picked);

		return new Lotto(picked);
	}

	List<Integer> getNumbers() {
		return this.numbers;
	}
}
