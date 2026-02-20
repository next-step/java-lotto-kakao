package lotto;

import java.util.*;

public class LottoGenerator {
    private LottoGenerator() {
    }

    private static final int LOTTO_SIZE = 6;

    public static Lotto generateLotto() {
        List<LottoNumber> candidateNumbers = new ArrayList<>(LottoNumber.all());
        Collections.shuffle(candidateNumbers);
        return new Lotto(new ArrayList<>(candidateNumbers.subList(0, LOTTO_SIZE)));
    }
}
