package domain;

import java.util.List;
import java.util.Objects;

public final class WinningLotto {
    public final Lotto lotto;
    public final LottoNumber bonus;

    public static WinningLotto of(List<Integer> numbers, int bonus) {
        return new WinningLotto(Lotto.of(numbers), LottoNumber.of(bonus));
    }

    private WinningLotto(Lotto lotto, LottoNumber bonus) {
        validateRequiredArguments(lotto, bonus);
        this.lotto = lotto;
        this.bonus = bonus;
    }

    private void validateRequiredArguments(Lotto lotto, LottoNumber bonus) {
        Objects.requireNonNull(lotto);
        Objects.requireNonNull(bonus);
    }

    public Lotto numbers() {
        return lotto;
    }

    public LottoNumber bonus() {
        return bonus;
    }
}
