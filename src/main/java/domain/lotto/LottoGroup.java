package domain.lotto;

import domain.winning.LottoResult;
import domain.winning.WinningLotto;
import domain.winning.WinningStatus;

import java.util.*;

public class LottoGroup {

    private final List<Lotto> manualLottos;
    private final List<Lotto> autoLottos;
    private final List<Lotto> allLottos;

    public LottoGroup(List<Lotto> manualLottos, List<Lotto> autoLottos) {
        this.manualLottos = manualLottos;
        this.autoLottos = autoLottos;

        List<Lotto> mergedLottos = new ArrayList<>();
        mergedLottos.addAll(manualLottos);
        mergedLottos.addAll(autoLottos);
        this.allLottos = List.copyOf(mergedLottos);
    }

    public LottoResult compare(WinningLotto winningLotto) {
        EnumMap<WinningStatus, Integer> counts = new EnumMap<>(WinningStatus.class);
        for (WinningStatus status : WinningStatus.values()) {
            counts.put(status, 0);
        }
        for (Lotto lotto : allLottos) {
            WinningStatus status = winningLotto.compare(lotto);
            counts.put(status, counts.get(status) + 1);
        }
        return new LottoResult(counts);
    }

    public int getManualLottosSize() {
        return manualLottos.size();
    }

    public int getAutoLottosSize() {
        return autoLottos.size();
    }

    public List<Lotto> getManualLottos() {
        return Collections.unmodifiableList(manualLottos);
    }

    public List<Lotto> getAutoLottos() {
        return Collections.unmodifiableList(autoLottos);
    }
}
