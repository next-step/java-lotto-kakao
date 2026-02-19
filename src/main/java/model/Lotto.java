package model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private static final int LOTTO_NUMBER_COUNT = 6;
    private final List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> numbers){
        validateCount(numbers.size());
        validateDuplicate(numbers);
        this.numbers = numbers;
    }

    public Lotto(LottoNumber... numbers) {
        this(Arrays.asList(numbers));
    }

    public Lotto(Integer... numbers) {
        this(toLottoNumbers(numbers));
    }

    private static List<LottoNumber> toLottoNumbers(Integer... numbers) {
        validateCount(numbers.length);
        return Arrays.stream(numbers)
                .map(LottoNumber::of)
                .collect(Collectors.toList());
    }

    private static void validateCount(int numbersCount) {
        if(numbersCount != LOTTO_NUMBER_COUNT) throw new RuntimeException("로또 번호 개수가 6개가 아닙니다.");
    }

    private static void validateDuplicate(List<LottoNumber> numbers) {
        if(!numbers.stream().allMatch(new HashSet<LottoNumber>()::add)) throw new RuntimeException("로또 번호는 중복될 수 없습니다");
    }

    public Integer size(){
        return numbers.size();
    }

    public int countMatches(List<Integer> winningNumbers) {
        int matchCount = 0;
        for (LottoNumber number : numbers) {
            if (winningNumbers.contains(number.getValue())) {
                matchCount++;
            }
        }
        return matchCount;
    }

    public boolean hasBonusNumber(Integer bonusNumber) {
        if (bonusNumber == null) {
            return false;
        }
        for (LottoNumber number : numbers) {
            if (number.getValue().equals(bonusNumber)) {
                return true;
            }
        }
        return false;
    }

    public List<Integer> getNumbers(){
        return numbers.stream()
                .map(LottoNumber::getValue)
                .collect(Collectors.toList());
    }
}
