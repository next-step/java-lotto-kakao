package lotto;

import java.util.HashSet;
import java.util.Set;

public class Lotto {
    private final Set<Integer> lottoNumberSet;


    public Lotto(Integer... lottoNumbers) {
        this(Set.of(lottoNumbers));
    }

    public Lotto(Set<Integer> lottoNumberSet) {
        this.lottoNumberSet = lottoNumberSet;
    }

    public int matchCount(Lotto buy) {
        Set<Integer> intersection = new HashSet<>(lottoNumberSet);
        intersection.retainAll(buy.lottoNumberSet);

        return intersection.size();
    }

    public boolean hasBonus(int bonus) {
        return lottoNumberSet.contains(bonus);
    }

}
