package lotto.domain.pick;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutoLottoNumberGenerator implements LottoPickStrategy {

    @Override
    public List<Integer> generate() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 45; i++) numbers.add(i);

        Collections.shuffle(numbers);
        List<Integer> result = numbers.subList(0, LOTTO_SIZE);
        Collections.sort(result);

        return result;
    }

}
