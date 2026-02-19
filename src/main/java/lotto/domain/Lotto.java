package lotto.domain;

import static lotto.domain.LottoPolicy.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import lombok.Getter;

@Getter
public class Lotto {

	private final List<LottoNumber> numbers;

	private Lotto(List<LottoNumber> numbers) {
		validate(numbers);
		List<LottoNumber> copied = new ArrayList<>(numbers);
		this.numbers = Collections.unmodifiableList(sort(copied));
	}

	public static Lotto from(List<Integer> values) {
		validateValues(values);
		List<LottoNumber> numbers = values.stream()
			.map(LottoNumber::from)
			.toList();
		return new Lotto(numbers);
	}

	private static void validate(Collection<LottoNumber> numbers) {
		validateNotNull(numbers);
		validateSize(numbers);
		validateDistinct(numbers);
	}

	private static void validateNotNull(Collection<LottoNumber> numbers) {
		if (numbers == null) {
			throw new IllegalArgumentException("번호는 null일 수 없습니다.");
		}
		boolean hasNull = numbers.stream().anyMatch(Objects::isNull);
		if (hasNull) {
			throw new IllegalArgumentException("로또 번호는 null일 수 없습니다.");
		}
	}

	private static void validateSize(Collection<LottoNumber> numbers) {
		if (numbers.size() != REQUIRED_LOTTO_SIZE) {
			throw new IllegalArgumentException(
				String.format("로또 번호는 %d개여야 합니다.", REQUIRED_LOTTO_SIZE));
		}
	}

	private static void validateDistinct(Collection<LottoNumber> numbers) {
		int uniqueCount = new HashSet<>(numbers).size();
		if (uniqueCount != numbers.size()) {
			throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
		}
	}

	private static void validateValues(List<Integer> values) {
		if (values == null) {
			throw new IllegalArgumentException("번호는 null일 수 없습니다.");
		}
		boolean hasNull = values.stream().anyMatch(Objects::isNull);
		if (hasNull) {
			throw new IllegalArgumentException("로또 번호는 null일 수 없습니다.");
		}
	}

	private static List<LottoNumber> sort(List<LottoNumber> numbers) {
		List<LottoNumber> sorted = new ArrayList<>(numbers);
		Collections.sort(sorted);
		return sorted;
	}
}
