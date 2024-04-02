package strategy;

import enumeration.LottoCondition;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static enumeration.LottoCondition.BEGIN;
import static enumeration.LottoCondition.END;

public final class AutoLottoNumberStrategy implements LottoNumberStrategy {
    private static final List<Integer> CANDIDATE_NUMBERS = IntStream.rangeClosed(BEGIN.value(), END.value())
            .boxed()
            .collect(Collectors.toList());

    private final int numberLength;

    public static AutoLottoNumberStrategy of(int numberLength) {
        return new AutoLottoNumberStrategy(numberLength);
    }

    private AutoLottoNumberStrategy(int numberLength) {
        validateNumberLength(numberLength);
        this.numberLength = numberLength;
    }

    private void validateNumberLength(int numberLength) {
        int maxNumberLength = LottoCondition.range();
        if (maxNumberLength < numberLength) {
            throw new IllegalArgumentException("최대 " + maxNumberLength + "의 길이로 된 로또 숫자만 사용할 수 있습니다.");
        }
    }

    @Override
    public List<Integer> perform() {
        Collections.shuffle(CANDIDATE_NUMBERS);
        return CANDIDATE_NUMBERS.subList(0, numberLength);
    }
}
