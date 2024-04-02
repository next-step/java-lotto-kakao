package controller;

import domain.LottoMachine;
import domain.Lottos;
import domain.WinningLotto;
import enumeration.Rank;
import strategy.LottoNumberStrategy;

import java.util.ArrayList;
import java.util.List;

public final class LottoGame {
    private final int cash;
    private final Lottos lottos;
    private List<Rank> ranks;

    public static LottoGame of(int cash, LottoNumberStrategy lottoNumberStrategy) {
        return new LottoGame(cash, lottoNumberStrategy);
    }

    private LottoGame(int cash, LottoNumberStrategy lottoNumberStrategy) {
        int bunchSize = LottoMachine.bunchSize(cash);
        this.cash = bunchSize * 1000;
        this.lottos = LottoMachine.issue(bunchSize, lottoNumberStrategy);
    }

    public void start(List<Integer> numbers, int bonus) {
        this.ranks = this.lottos.scratch(WinningLotto.of(numbers, bonus));
    }

    public int cash() {
        return cash;
    }

    public Lottos lottos() {
        return lottos;
    }

    public List<Rank> ranks() {
        return new ArrayList<>(ranks);
    }
}
