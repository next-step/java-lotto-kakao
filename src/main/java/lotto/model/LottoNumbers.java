package lotto.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoNumbers {
    private static final int LOTTO_SIZE = 6;

    private final List<LottoNumber> numbers;

    public LottoNumbers(List<Integer> numbers) {
        // 숫자 개수 검증
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개의 숫자이어야 합니다.");
        }
        // 숫자 중복 검증
        final Set<Integer> set = new HashSet<>(numbers);
        if (set.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
        this.numbers = convertNumbers(numbers);
    }

    public List<Integer> getNumbers() {
        return numbers
            .stream()
            .map(LottoNumber::value)
            .toList();
    }

    private static List<LottoNumber> convertNumbers(List<Integer> numbers) {
        return numbers
            .stream()
            .map(LottoNumber::new)
            .toList();
    }

    public String toString() {
        return getNumbers().toString();
    }
}
