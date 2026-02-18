package lotto.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {

    public static final int REQUIRED_SIZE = 6;

    public static final String COUNT_FAIL_MSG = "입력된 숫자가 6개가 아닙니다.";
    public static final String DUPLICATE_FAIL_MSG = "중복된 숫자가 입력되었습니다.";

    private final List<LottoNumber> numbers;

    public static Lotto fromIntegers(List<Integer> values) {
        validate(values);

        List<LottoNumber> lottoNumbers = values.stream()
                .map(LottoNumber::of)
                .toList();

        return Lotto.fromNumbers(lottoNumbers);
    }

    public static Lotto fromNumbers(List<LottoNumber> values) {
        List<Integer> ints = values.stream().map(LottoNumber::getValue).toList();
        validate(ints);
        return new Lotto(values);
    }

    public static Lotto of(int... values) {
        return fromIntegers(Arrays.stream(values).boxed().toList());
    }


    private Lotto(List<LottoNumber> values) {
        this.numbers = List.copyOf(values);
    }

    private Lotto(Lotto other) {
        this.numbers = other.numbers;
    }

    private static void validate(List<Integer> numbers) {
        validateDuplicate(numbers);
        validateNumberCount(numbers);
    }

    private static void validateNumberCount(List<Integer> numbers) {
        if (numbers.size() != REQUIRED_SIZE) {
            throw new IllegalArgumentException(COUNT_FAIL_MSG);
        }
    }

    private static void validateDuplicate(List<Integer> numbers) {
        Set<Integer> numberSet = new HashSet<>(numbers);
        if (numberSet.size() != numbers.size()) {
            throw new IllegalArgumentException(DUPLICATE_FAIL_MSG);
        }
    }

    public List<Integer> getNumbers() {
        return numbers.stream()
                .map(LottoNumber::getValue)
                .toList();
    }

    public int matchCount(Lotto other) {
        int count = 0;
        Set<Integer> set = new HashSet<>(other.getNumbers());
        for (LottoNumber number : this.numbers) {
            if (set.contains(number.getValue())) {
                count++;
            }
        }
        return count;
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
