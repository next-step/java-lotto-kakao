package lotto;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoGenerator {

    public Lotto generate() {
        List<LottoNumber> pool = LottoNumber.allNumbers();
        Collections.shuffle(pool);
        Set<LottoNumber> numbers = new HashSet<>(pool.subList(0, Lotto.numberCount()));
        return new Lotto(numbers);
    }
}
