package com.lotto.util;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumberGenerator implements LottoGenerateStrategy {

    private static final int LOTTO_NUMBER_START = 1;
    private static final int LOTTO_NUMBER_END = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;

    @Override
    public List<Integer> generate() {
        List<Integer> numbers = IntStream.rangeClosed(LOTTO_NUMBER_START, LOTTO_NUMBER_END)
                .boxed()
                .collect(Collectors.toList());

        Collections.shuffle(numbers);

        return numbers.stream()
                .limit(LOTTO_NUMBER_COUNT)
                .sorted()
                .collect(Collectors.toList());
    }
}
