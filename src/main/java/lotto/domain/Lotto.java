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
    private final Set<Integer> numberSet;

    public static Lotto fromIntegers(List<Integer> values) {
        validate(values);

        List<LottoNumber> lottoNumbers = values.stream()
                .map(LottoNumber::of)
                .toList();

        Set<Integer> set = new HashSet<>(values);
        return new Lotto(lottoNumbers, Set.copyOf(set));
    }

    public Lotto(List<Integer> values) {
        this(Lotto.fromIntegers(values));
    }

    public Lotto(int... values) {
        this(Arrays.stream(values).boxed().toList());
    }

    private Lotto(Lotto other) {
        this.numbers = other.numbers;
        this.numberSet = other.numberSet;
    }

    private Lotto(List<LottoNumber> numbers, Set<Integer> numberSet) {
        this.numbers = List.copyOf(numbers);
        this.numberSet = Set.copyOf(numberSet);
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

    public Set<Integer> getNumberSet() {
        return numberSet;
    }

    public int matchCount(Lotto other) {
        int count = 0;
        for (LottoNumber number : this.numbers) {
            if (other.getNumberSet().contains(number.getValue())) {
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
