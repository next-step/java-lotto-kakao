package controller;

import domain.LottoMachine;
import domain.Lottos;
import domain.WinningLotto;
import strategy.LottoNumberStrategy;

import java.util.List;

public final class LottoGame {
    private final int cash;
    private final Lottos lottos;
    private final WinningLotto winningLotto;
    private long prize;

    public static LottoGame of(int cash, List<Integer> numbers, int bonus, LottoNumberStrategy lottoNumberStrategy) {
        return new LottoGame(cash, numbers, bonus, lottoNumberStrategy);
    }

    private LottoGame(int cash, List<Integer> numbers, int bonus, LottoNumberStrategy lottoNumberStrategy) {
        int bunchSize = LottoMachine.bunchSize(cash);
        this.cash = bunchSize * 1000;
        this.lottos = LottoMachine.issue(bunchSize, lottoNumberStrategy);
        this.winningLotto = WinningLotto.of(numbers, bonus);
    }

    public void start() {
        this.prize = this.lottos.scratch(winningLotto);
    }

    public double profitRate() {
        return (double) prize / (double) cash;
    }
}
