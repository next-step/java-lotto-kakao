package lotto;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private final List<LottoNumber> lottoNumbers;
    private static final int LOTTO_COUNT = 6;

    public Lotto(List<LottoNumber> lottoNumbers) {
        validateSize(lottoNumbers);
        lottoNumbers.sort(Comparator.comparingInt(LottoNumber::toNumber).reversed());
        this.lottoNumbers = lottoNumbers;
    }

    public int toLottoNumbersSize() {
        return lottoNumbers.size();
    }

    public int matchCount(Lotto other) {
        return (int) lottoNumbers.stream()
                .filter(other::contains) // 아래 contains 메서드 활용
                .count();
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    private static void validateSize(List<LottoNumber> lottoNumbers){
        if (lottoNumbers.size() != LOTTO_COUNT){
            throw new IllegalArgumentException("로또번호가 "+ LOTTO_COUNT + "개가 아닙니다.");
        }
    }

    @Override
    public String toString() {
        return lottoNumbers.stream()
                .map(LottoNumber::getNumber) // LottoNumber 객체에서 숫자(int)만 추출
                .sorted()                    // 오름차순 정렬 (요구사항)
                .collect(Collectors.toList())
                .toString();                 // [1, 2, 3, 4, 5, 6] 형태로 반환
    }
}
