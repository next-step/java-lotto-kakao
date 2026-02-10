package lotto.domain;

import java.util.*;

public class Lotto {

    private final List<LottoNumber> numbers;

    public Lotto(List<Integer> values) {
        validate(values);
        numbers = new ArrayList<>();
        for (int number: values) {
            numbers.add(new LottoNumber(number));
        }
    }

    private void validate(List<Integer> numbers) {
        validateDuplicate(numbers);
        validateNumberCount(numbers);
    }

    private void validateNumberCount(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("입력된 숫자가 6개가 아닙니다.");
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        Set<Integer> numberSet = new HashSet<>(numbers);
        if (numberSet.size() != numbers.size()) {
            throw new IllegalArgumentException("중복된 숫자가 입력되었습니다.");
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

}
