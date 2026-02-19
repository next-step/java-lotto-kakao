package domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class AutoLottoGenerator implements LottoGenerator {

    private final Random random;

    public AutoLottoGenerator() {
        this.random = new Random();
    }

    public AutoLottoGenerator(Random random) {
        this.random = random;
    }

    @Override
    public List<Integer> generate() {
        List<Integer> shuffleNumbers = new ArrayList<>();
        for (int i = LottoNumber.MIN_NUMBER; i <= LottoNumber.MAX_NUMBER; i++) {
            shuffleNumbers.add(i);
        }
        Collections.shuffle(shuffleNumbers, random);
        List<Integer> numbers = new ArrayList<>(shuffleNumbers.subList(0, Lotto.LOTTO_SIZE));
        Collections.sort(numbers);
        return numbers;
    }
}
