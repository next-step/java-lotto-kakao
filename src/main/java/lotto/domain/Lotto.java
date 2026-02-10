package lotto.domain;

import java.util.*;

public class Lotto {

    private List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        for (int number : numbers) {
            ValidateRange(number);
        }
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

    private void ValidateRange(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("1 ~ 45 범위를 벗어나는 숫자가 입력되었습니다.");
        }
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }
}
