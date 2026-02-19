package domain.lotto;

import domain.winning.LottoResult;
import domain.winning.WinningLotto;
import domain.winning.WinningStatus;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Stream;

public class LottoGroup {

    private final List<Lotto> lottoList;

    public LottoGroup(List<Lotto> lottoList) {
        this.lottoList = lottoList;
    }

    public LottoResult compare(WinningLotto winningLotto) {
        EnumMap<WinningStatus, Integer> counts = new EnumMap<>(WinningStatus.class);
        for (WinningStatus status : WinningStatus.values()) {
            counts.put(status, 0);
        }
        for (Lotto lotto : lottoList) {
            WinningStatus status = winningLotto.compare(lotto);
            counts.put(status, counts.get(status) + 1);
        }
        return new LottoResult(counts);
    }

    public int getSize() {
        return lottoList.size();
    }

    public LottoGroup concat(LottoGroup other) {
        List<Lotto> merged = Stream.concat(lottoList.stream(), other.lottoList.stream()).toList();
        return new LottoGroup(merged);
    }

    public List<Lotto> getLottoList() {
        return lottoList;
    }
}