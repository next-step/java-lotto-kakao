package lotto;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGenerator {
    private LottoGenerator() {
    }

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 46;
    private static final int LOTTO_SIZE = 6;
    private static final List<LottoNumber> numbers = IntStream.range(MIN_NUMBER, MAX_NUMBER)
            .mapToObj(LottoNumber::new)
            .collect(Collectors.toList());

    public static Lotto generateLotto() {
        Collections.shuffle(numbers);
        return new Lotto(new ArrayList<>(numbers.subList(0, LOTTO_SIZE)));
    }
}
