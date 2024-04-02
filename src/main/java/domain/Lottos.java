package domain;

import enumeration.Rank;
import strategy.LottoNumberStrategy;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Lottos {
    private final List<Lotto> bunch;

    public static Lottos of(int bunchSize, LottoNumberStrategy strategy) {
        return new Lottos(bunchSize, strategy);
    }

    private Lottos(int bunchSize, LottoNumberStrategy strategy) {
        this.bunch = IntStream.range(0, bunchSize)
                .mapToObj(e -> Lotto.of(strategy.perform()))
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Lotto> bunch() {
        return bunch;
    }

    public List<Rank> scratch(WinningLotto winningLotto) {
        return bunch.stream()
                .map(e -> Rank.of(e, winningLotto))
                .collect(Collectors.toList());
    }
}
