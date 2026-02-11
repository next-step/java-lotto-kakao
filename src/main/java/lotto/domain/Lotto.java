package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {

    public static final int REQUIRED_SIZE = 6;

    public static final String COUNT_FAIL_MSG = "입력된 숫자가 6개가 아닙니다.";
    public static final String DUPLICATE_FAIL_MSG = "중복된 숫자가 입력되었습니다.";

    private final List<LottoNumber> numbers;

    public Lotto(List<Integer> values) {
        validate(values);
        numbers = new ArrayList<>();
        for (int number: values) {
            numbers.add(new LottoNumber(number));
        }
    }

    public Lotto(int... values) {
        this(Arrays.stream(values).boxed().toList());
    }

    private void validate(List<Integer> numbers) {
        validateDuplicate(numbers);
        validateNumberCount(numbers);
    }

    private void validateNumberCount(List<Integer> numbers) {
        if (numbers.size() != REQUIRED_SIZE) {
            throw new IllegalArgumentException(COUNT_FAIL_MSG);
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        Set<Integer> numberSet = new HashSet<>(numbers);
        if (numberSet.size() != numbers.size()) {
            throw new IllegalArgumentException(DUPLICATE_FAIL_MSG);
        }
    }


    public List<Integer> getNumbers() {
        List<Integer> result = new ArrayList<>();
        for (LottoNumber number: numbers) {
            result.add(number.getValue());
        }
        return Collections.unmodifiableList(result);
    }

    public int matchCount(Lotto other) {
        HashSet<Integer> otherNumbers = new HashSet<>(other.getNumbers());
        otherNumbers.retainAll(this.getNumbers());
        return otherNumbers.size();
    }

    public boolean contains(LottoNumber number) {
        return numbers.contains(number);
    }

    @Override
    public String toString() {
        return numbers.stream()
                .map(LottoNumber::getValue)
                .map(String::valueOf)
                .collect(Collectors.joining(", ", "[", "]"));
    }
}
