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
		// ToDo: LottoNumber 클래스에서 min, max 값 설정 후 매직 넘버 수정
		List<Integer> pool = IntStream.rangeClosed(1, 45)
			.boxed()
			.collect(Collectors.toList());

		Collections.shuffle(pool);
		// ToDo: Application 클래스 리팩토링 시 Lotto 길이 검증 로직 추가 후 매직 넘버 수정
		List<Integer> picked = new ArrayList<>(pool.subList(0, 6));
		Collections.sort(picked);

		return new Lotto(picked);
	}

	List<Integer> getNumbers() {
		return this.numbers;
	}
}
