package lotto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lotto {

	private List<Integer> numbers;

	Lotto() {
		List<Integer> pool = IntStream.rangeClosed(1, 45)
			.boxed()
			.collect(Collectors.toList());

		Collections.shuffle(pool);
		this.numbers = pool.subList(0, 6);
		Collections.sort(this.numbers);
	}

	List<Integer> getNumbers() {
		return this.numbers;
	}
}
