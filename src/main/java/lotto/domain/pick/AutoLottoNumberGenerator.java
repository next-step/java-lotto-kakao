package lotto.domain.pick;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class AutoLottoNumberGenerator implements LottoPickStrategy {

    private static final List<LottoNumber> POOL =
            IntStream.rangeClosed(LottoNumber.MIN_LOTTO_NUMBER, LottoNumber.MAX_LOTTO_NUMBER)
                    .boxed()
                    .map(LottoNumber::of)
                    .toList();

    @Override
    public List<LottoNumber> generate() {
        List<LottoNumber> numbers = new ArrayList<>(POOL);
        Collections.shuffle(numbers);
        return numbers.subList(0, Lotto.REQUIRED_SIZE).stream()
                .sorted(Comparator.comparingInt(LottoNumber::getValue))
                .toList();
    }

}
