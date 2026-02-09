package lotto;

import java.util.HashSet;
import java.util.Set;

public class Lotto {
    private static final int LENGTH = 6;

    private final Set<LottoNumber> lottoNumberSet;

    public Lotto(Integer... numbers) {
        this(convert(numbers));
    }

    public Lotto(Set<LottoNumber> lottoNumberSet) {
        validate(lottoNumberSet);
        this.lottoNumberSet = lottoNumberSet;
    }

    private static Set<LottoNumber> convert(Integer[] numbers) {
        Set<LottoNumber> result = new HashSet<>();
        for (Integer number : numbers) {
            result.add(new LottoNumber(number));
        }
        return result;
    }

    private void validate(Set<LottoNumber> lottoNumberSet) {
        if (lottoNumberSet.size() != LENGTH) {
            throw new IllegalArgumentException(
                    String.format("로또 숫자는 %d개여야 합니다", LENGTH)
            );
        }
    }

    public int matchCount(Lotto buy) {
        Set<LottoNumber> intersection = new HashSet<>(lottoNumberSet);
        intersection.retainAll(buy.lottoNumberSet);

        return intersection.size();
    }

    public boolean hasBonus(LottoNumber bonus) {
        return lottoNumberSet.contains(bonus);
    }

}
