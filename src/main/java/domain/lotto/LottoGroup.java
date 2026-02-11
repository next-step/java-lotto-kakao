package domain.lotto;

import domain.winning.LottoResult;
import domain.winning.WinningLotto;
import domain.winning.WinningStatus;

import java.util.EnumMap;
import java.util.List;

public class LottoGroup {

    private final List<Lotto> lottos;

    public LottoGroup(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public LottoResult compare(WinningLotto winningLotto) {
        EnumMap<WinningStatus, Integer> counts = new EnumMap<>(WinningStatus.class);
        for (WinningStatus status : WinningStatus.values()) {
            counts.put(status, 0);
        }
        for (Lotto lotto : lottos) {
            WinningStatus status = winningLotto.compare(lotto);
            counts.put(status, counts.get(status) + 1);
        }
        return new LottoResult(counts);
    }

    public int getSize() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}