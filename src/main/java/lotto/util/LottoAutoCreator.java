package lotto.util;

import lotto.domain.LottoNumber;

import java.util.*;
import java.util.stream.Collectors;

public class LottoAutoCreator {

    public static Set<LottoNumber> lottoAutoCreate() {
        return getLottoBallList();
    }

    private static Set<LottoNumber> getLottoBallList() {
        List<Integer> numbers = new ArrayList<>();

        for (int i = 1; i <= 45; i++) {
            numbers.add(i);
        }

        Collections.shuffle(numbers);

        List<Integer> selected = numbers.subList(0, 6);

        return selected.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toSet());
    }
}
