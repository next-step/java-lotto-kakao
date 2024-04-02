package domain;

import enumeration.LottoCondition;
import strategy.LottoNumberStrategy;

public final class LottoMachine {
    private final int cash;
    private final Lottos lottos;

    public static LottoMachine issue(int bunchSize, LottoNumberStrategy lottoNumberStrategy) {
        return new LottoMachine(bunchSize, lottoNumberStrategy);
    }

    private LottoMachine(int bunchSize, LottoNumberStrategy lottoNumberStrategy) {
        this.cash = bunchSize * 1000;
        this.lottos = Lottos.of(bunchSize, lottoNumberStrategy);
    }

    public static int bunchSize(int cash) {
        return cash / LottoCondition.PRICE.value();
    }

    public int bunchSize() {
        return lottos.bunch().size();
    }
}
