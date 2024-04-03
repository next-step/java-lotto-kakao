package domain;

import enumeration.LottoCondition;
import strategy.LottoNumberStrategy;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class LottoMachine {
    public static Lottos issue(int bunchSize, LottoNumberStrategy lottoNumberStrategy) {
        return Lottos.of(generateBunchByStrategy(bunchSize, lottoNumberStrategy));
    }

    private static List<Lotto> generateBunchByStrategy(int bunchSize, LottoNumberStrategy lottoNumberStrategy) {
        return IntStream.range(0, bunchSize)
                .mapToObj(e -> Lotto.of(lottoNumberStrategy.perform()))
                .collect(Collectors.toList());
    }

    public static int bunchSize(int cash) {
        return cash / LottoCondition.PRICE.value();
    }
}
