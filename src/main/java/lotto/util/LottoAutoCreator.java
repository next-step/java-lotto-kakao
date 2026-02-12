package lotto.util;

import lotto.domain.LottoBalls;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class LottoAutoCreator {

    public static LottoBalls lottoAutoCreate() {

        return getLottoBallList(6);
    }

    private static LottoBalls getLottoBallList(int toIndex) {
        ArrayList<Integer> numbers = new ArrayList<>();

        for (int i = 1; i <= 45; i++) {
            numbers.add(i);
        }

        Collections.shuffle(numbers);

        ArrayList<Integer> result = new ArrayList<>(numbers.subList(0, toIndex));
        Collections.sort(result);

        return new LottoBalls(new HashSet<>(result));
    }
}
