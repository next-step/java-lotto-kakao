package model;

import java.util.List;

public class LottoNumbers {

    private static final int SIZE = 6;

    private final List<LottoNumber> numbers;

    public LottoNumbers(List<LottoNumber> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public List<LottoNumber> getNumbers() {
        return numbers;
    }

    private void validate(List<LottoNumber> numbers) {
        if (numbers.size() != SIZE) {
            throw new IllegalArgumentException("숫자는 " + SIZE + "개만 입력해야 합니다.");
        }
        long key = 0L;
        for (LottoNumber number : numbers) {
            if ((key & (1L << number.getNumber())) == 1) {
                throw new IllegalArgumentException("중복된 숫자를 입력할 수 없습니다.");
            }
            key |= 1L << number.getNumber();
        }
    }
}
