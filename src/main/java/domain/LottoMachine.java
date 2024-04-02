package domain;

import enumeration.LottoCondition;
import strategy.LottoNumberStrategy;

public final class LottoMachine {
    public static Lottos issue(int bunchSize, LottoNumberStrategy lottoNumberStrategy) {
        return Lottos.of(bunchSize, lottoNumberStrategy);
    }

    public static int bunchSize(int cash) {
        return cash / LottoCondition.PRICE.value();
    }
}
