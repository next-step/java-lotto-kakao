package domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class AutoLottoFactory implements LottoFactory {

    private final int count;
    private final Random random;

    public AutoLottoFactory(int count) {
        this.count = count;
        this.random = new Random();
    }

    public AutoLottoFactory(int count, Random random) {
        this.count = count;
        this.random = random;
    }

    @Override
    public List<Lotto> create() {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            List<Integer> numbers = generateRandomNumbers();
            Lotto lotto = new Lotto(numbers);
            lottos.add(lotto);
        }
        return lottos;
    }

    private List<Integer> generateRandomNumbers() {
        List<Integer> shuffleNumbers = new ArrayList<>();
        for (int i = Lotto.MIN_NUMBER; i <= Lotto.MAX_NUMBER; i++) {
            shuffleNumbers.add(i);
        }
        Collections.shuffle(shuffleNumbers, random);
        List<Integer> numbers = new ArrayList<>(shuffleNumbers.subList(0, Lotto.LOTTO_SIZE));
        Collections.sort(numbers);
        return numbers;
    }
}
