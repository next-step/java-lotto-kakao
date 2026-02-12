package lotto.util;

import lotto.domain.LottoBalls;
import lotto.domain.LottoNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class LottoAutoCreator {

    public static LottoBalls lottoCreate() {

        ArrayList<Integer> numbers = new ArrayList<>();

        for (int i = LottoNumber.getLottoMinNum(); i <= LottoNumber.getLottoMaxNum(); i++) {
            numbers.add(i);
        }

        Collections.shuffle(numbers);

        ArrayList<Integer> result = new ArrayList<>(numbers.subList(0, LottoBalls.getLottoLength()));
        Collections.sort(result);

        return new LottoBalls(new HashSet<>(result));
    }
}
