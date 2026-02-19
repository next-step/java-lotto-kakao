package lotto.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import lombok.Getter;
import lotto.exception.LottoValidationException;

@Getter
public class Lotto {
	private static final int REQUIRED_SIZE = 6;

	private final List<LottoNumber> numbers;

	private Lotto(List<LottoNumber> numbers) {
		validate(numbers);
		List<LottoNumber> copied = new ArrayList<>(numbers);
		sort(copied);
		this.numbers = Collections.unmodifiableList(copied);
	}

	public static Lotto from(List<Integer> values) {
		validateValues(values);
		List<LottoNumber> numbers = values.stream()
			.map(LottoNumber::from)
			.toList();
		return new Lotto(numbers);
	}

	public static int requiredSize() {
		return REQUIRED_SIZE;
	}

	private static void validate(Collection<LottoNumber> numbers) {
		validateNotNull(numbers);
		validateSize(numbers);
		validateDistinct(numbers);
	}

	private static void validateNotNull(Collection<LottoNumber> numbers) {
		if (numbers == null) {
			throw new LottoValidationException("번호는 null일 수 없습니다.");
		}
		boolean hasNull = numbers.stream().anyMatch(Objects::isNull);
		if (hasNull) {
			throw new LottoValidationException("로또 번호에 null이 포함될 수 없습니다.");
		}
	}

	private static void validateSize(Collection<LottoNumber> numbers) {
		if (numbers.size() != REQUIRED_SIZE) {
			throw new LottoValidationException(String.format("로또 번호는 %d개여야 합니다.", REQUIRED_SIZE));
		}
	}

	private static void validateDistinct(Collection<LottoNumber> numbers) {
		int uniqueCount = new HashSet<>(numbers).size();
		if (uniqueCount != numbers.size()) {
			throw new LottoValidationException("로또 번호는 중복될 수 없습니다.");
		}
	}

	private static void validateValues(List<Integer> values) {
		if (values == null) {
			throw new LottoValidationException("번호는 null일 수 없습니다.");
		}
		boolean hasNull = values.stream().anyMatch(Objects::isNull);
		if (hasNull) {
			throw new LottoValidationException("로또 번호에 null이 포함될 수 없습니다.");
		}
	}

	private static void sort(List<LottoNumber> numbers) {
		Collections.sort(numbers);
	}
}
