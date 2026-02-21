package domain.lotto;

import java.util.*;

public class Lotto {

    public static final int PRICE = 1000;
    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 45;
    public static final int LOTTO_SIZE = 6;

    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<Integer> numbers) {
        validateLotto(numbers);
        this.lottoNumbers = generateLottoNumbers(numbers);
    }

    private List<LottoNumber> generateLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::of)
                .toList();
    }

    private void validateLotto(List<Integer> numbers){
        validateSize(numbers);
        validateDuplicates(numbers);
    }

    private void validateSize(List<Integer> numbers){
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    private void validateDuplicates(List<Integer> numbers){
        Set<Integer> numberSet = new HashSet<>(numbers);
        if (numberSet.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호에 중복이 있습니다.");
        }
    }

    public List<LottoNumber> getNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }

}
