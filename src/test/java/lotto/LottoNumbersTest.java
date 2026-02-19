package lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumbersTest {

	@Test
	@DisplayName("로또 번호는 6개가 아니면 생성할 수 없다")
	void create_fail_when_number_count_is_not_six() {
		assertThatThrownBy(() -> new LottoNumbers(numbers(1, 2, 3, 4, 5)))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("로또 번호는 중복되면 생성할 수 없다")
	void create_fail_when_numbers_are_duplicated() {
		assertThatThrownBy(() -> new LottoNumbers(numbers(1, 1, 2, 3, 4, 5)))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("생성 시 번호는 오름차순 정렬된다")
	void create_sorted_numbers() {
		LottoNumbers lottoNumbers = new LottoNumbers(numbers(45, 1, 3, 2, 10, 7));
		assertThat(lottoNumbers.values())
			.extracting(LottoNumber::getValue)
			.containsExactly(1, 2, 3, 7, 10, 45);
	}

	@Test
	@DisplayName("random은 중복 없는 6개의 번호를 생성한다")
	void random_generate_unique_six_numbers() {
		LottoNumbers lottoNumbers = LottoNumbers.random();
		ArrayList<Integer> values = lottoNumbers.values().stream()
			.map(LottoNumber::getValue)
			.collect(Collectors.toCollection(ArrayList::new));

		assertThat(values).hasSize(6);
		assertThat(values).doesNotHaveDuplicates();
		assertThat(values).allMatch(value -> value >= 1 && value <= 45);
	}

	@Test
	@DisplayName("matchCount는 다른 로또 번호와 일치 개수를 반환한다")
	void match_count() {
		LottoNumbers first = new LottoNumbers(numbers(1, 2, 3, 4, 5, 6));
		LottoNumbers second = new LottoNumbers(numbers(1, 2, 3, 7, 8, 9));
		assertThat(first.matchCount(second)).isEqualTo(3);
	}

	private ArrayList<LottoNumber> numbers(int... values) {
		ArrayList<LottoNumber> lottoNumbers = new ArrayList<>();
		for (int value : Arrays.stream(values).boxed().toList()) {
			lottoNumbers.add(LottoNumber.from(value));
		}
		return lottoNumbers;
	}
}
