package lotto;

import java.util.HashSet;
import java.util.Set;

public class Lotto {
    private final Set<LottoNumber> lottoNumberSet;


    public Lotto(Integer... numbers) {
        this(convert(numbers));
    }

    public Lotto(Set<LottoNumber> lottoNumberSet) {
        this.lottoNumberSet = lottoNumberSet;
    }

    private static Set<LottoNumber> convert(Integer[] numbers) {
        Set<LottoNumber> result = new HashSet<>();
        for (Integer number : numbers) {
            result.add(new LottoNumber(number));
        }
        return result;
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
