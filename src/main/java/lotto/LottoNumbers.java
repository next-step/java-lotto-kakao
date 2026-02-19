package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LottoNumbers {
	private static final int LOTTO_NUMBER_COUNT = 6;
	private static final int MIN_LOTTO_NUMBER = 1;
	private static final int MAX_LOTTO_NUMBER = 45;
	private final List<LottoNumber> numbers;

	public LottoNumbers(List<LottoNumber> numbers) {
		validateNumberCount(numbers);
		validateUniqueNumbers(numbers);
		this.numbers = new ArrayList<>(numbers);
		sortNumbers();
	}

	public static LottoNumbers random() {
		List<Integer> numberCandidates = createNumberCandidates();
		Collections.shuffle(numberCandidates);
		return new LottoNumbers(convertToLottoNumbers(numberCandidates));
	}

	public List<LottoNumber> values() {
		return new ArrayList<>(numbers);
	}

	public boolean contains(LottoNumber lottoNumber) {
		return numbers.contains(lottoNumber);
	}

	public int matchCount(LottoNumbers otherNumbers) {
		return (int) numbers.stream()
			.filter(otherNumbers::contains)
			.count();
	}

	private void validateNumberCount(List<LottoNumber> numbers) {
		if (numbers.size() != LOTTO_NUMBER_COUNT) {
			throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
		}
	}

	private void validateUniqueNumbers(List<LottoNumber> numbers) {
		List<LottoNumber> uniqueNumbers = new ArrayList<>();
		for (LottoNumber lottoNumber : numbers) {
			validateNotDuplicated(uniqueNumbers, lottoNumber);
			uniqueNumbers.add(lottoNumber);
		}
	}

	private void validateNotDuplicated(List<LottoNumber> uniqueNumbers, LottoNumber lottoNumber) {
		if (uniqueNumbers.contains(lottoNumber)) {
			throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
		}
	}

	private static List<Integer> createNumberCandidates() {
		List<Integer> numberCandidates = new ArrayList<>();
		for (int lottoNumberValue = MIN_LOTTO_NUMBER; lottoNumberValue <= MAX_LOTTO_NUMBER; lottoNumberValue++) {
			numberCandidates.add(lottoNumberValue);
		}
		return numberCandidates;
	}

	private static List<LottoNumber> convertToLottoNumbers(List<Integer> numberCandidates) {
		List<LottoNumber> lottoNumbers = new ArrayList<>();
		for (Integer number : numberCandidates.subList(0, LOTTO_NUMBER_COUNT)) {
			lottoNumbers.add(LottoNumber.from(number));
		}
		return lottoNumbers;
	}

	private void sortNumbers() {
		numbers.sort(Comparator.comparingInt(LottoNumber::getValue));
	}
}
