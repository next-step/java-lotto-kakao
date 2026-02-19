package lotto.domain;

import java.util.*;

public class Lotto {

    public static final String NOT_SIX_NUMBERS_EXCEPTION = "입력된 숫자가 6개가 아닙니다.";
    public static final String DUPLICATE_NUMBER_EXCEPTION = "중복된 숫자가 입력되었습니다.";

    private List<LottoNumber> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = new ArrayList<>();
        for (int number : numbers) {
            this.numbers.add(LottoNumber.of(number));
        }
    }

    private void validate(List<Integer> numbers) {
        validateDuplicate(numbers);
        validateNumberCount(numbers);
    }

    private void validateNumberCount(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(NOT_SIX_NUMBERS_EXCEPTION);
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        Set<Integer> numberSet = new HashSet<>(numbers);
        if (numberSet.size() != numbers.size()) {
            throw new IllegalArgumentException(DUPLICATE_NUMBER_EXCEPTION);
        }
    }

    public List<LottoNumber> getNumbers() {
        return Collections.unmodifiableList(this.numbers);
    }

    public int matchCount(Lotto userLotto) {
        Set<LottoNumber> userSet = new HashSet<>(userLotto.numbers);
        userSet.retainAll(new HashSet<>(this.numbers));
        return userSet.size();
    }
}
