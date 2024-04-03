package lotto.domain;

import static lotto.domain.Lotto.*;
import static lotto.domain.LottoNumbers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoMachine {

    public static final int LOTTO_PRICE = 1000;
    private final Random random;

    public LottoMachine() {
        this.random = new Random();
    }

    public List<Lotto> issue(int money) {
        validateMoney(money);

        return Stream.generate(this::issue)
            .limit(money / LOTTO_PRICE)
            .collect(Collectors.toList());
    }

    private void validateMoney(int money) {
        if (money < LOTTO_PRICE) {
            throw new IllegalArgumentException("로또를 한장도 구매할 수 없습니다");
        }
    }

    private Lotto issue() {
        List<Integer> numbers = new ArrayList<>();

        for (int poolSize = MAX_LOTTO_NUMBER - LOTTO_SIZE + 1; poolSize <= MAX_LOTTO_NUMBER; ++poolSize) {
            Integer number = random.nextInt(poolSize) + MIN_LOTTO_NUMBER;
            numbers.add(nextNumber(numbers, number, poolSize));
        }

        return new Lotto(numbers);
    }

    private Integer nextNumber(List<Integer> numbers, Integer number, int poolSize) {
        if (numbers.contains(number)) {
            return poolSize;
        }
        return number;
    }
}
