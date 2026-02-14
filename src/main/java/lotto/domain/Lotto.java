package lotto.domain;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private static final int LOTTO_SIZE = 6;
    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        validateSize(lottoNumbers);
        lottoNumbers.sort(Comparator.comparingInt(LottoNumber::getNumber).reversed());
        this.lottoNumbers = lottoNumbers;
    }

    public Lotto(int... values) {
        this(Arrays.stream(values)
            .mapToObj(LottoNumber::new)
            .collect(Collectors.toList())
        );
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    public int countMatchingNumbers(Lotto other) {
        return (int) lottoNumbers.stream()
                .filter(other::contains) // 아래 contains 메서드 활용
                .count();
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    private static void validateSize(List<LottoNumber> lottoNumbers){
        if (lottoNumbers.size() != LOTTO_SIZE){
            throw new IllegalArgumentException("로또번호가 6개가 아닙니다.");
        }
    }
    public List<Integer> getNumbers() {
        return lottoNumbers.stream()
            .map(LottoNumber::getNumber)
            .sorted()
            .toList();
    }
}
