package lotto.util;

import lotto.domain.LottoBalls;
import lotto.domain.LottoNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class NumberAutoCreator implements NumberCreator {

    @Override
    public Set<Integer> numberCreate() {

        ArrayList<Integer> numbers = new ArrayList<>();

        for (int i = LottoNumber.getMinNum(); i <= LottoNumber.getMaxNum(); i++) {
            numbers.add(i);
        }

        Collections.shuffle(numbers);

        ArrayList<Integer> result = new ArrayList<>(numbers.subList(0, LottoBalls.getLottoLength()));
        Collections.sort(result);

        return new HashSet<>(result);
    }
}
