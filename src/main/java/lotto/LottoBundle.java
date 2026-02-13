package lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LottoBundle {
    private final List<Lotto> lottos;

    LottoBundle(Lotto first, Lotto... rest) {
        this(mergeToList(first, rest));
    }

    LottoBundle(List<Lotto> lottos) {
        validate(lottos);
        this.lottos = List.copyOf(lottos);
    }

    private static List<Lotto> mergeToList(Lotto first, Lotto... rest) {
        if (first == null || rest == null) {
            throw new IllegalArgumentException("로또 묶음에 null이 포함될 수 없습니다.");
        }
        List<Lotto> list = new ArrayList<>();
        list.add(first);
        list.addAll(Arrays.asList(rest));
        return list;
    }

    private void validate(List<Lotto> lottos) {
        if (lottos == null || lottos.contains(null)) {
            throw new IllegalArgumentException("로또 묶음에 null이 포함될 수 없습니다.");
        }
    }

    public LottoBundleResult evaluate(WinLotto win) {
        LottoBundleResultBuilder lottoBundleResultBuilder = new LottoBundleResultBuilder();
        for (Lotto lotto : lottos) {
            LottoRank rank = win.lottery(lotto);
            lottoBundleResultBuilder.count(rank);
        }
        return lottoBundleResultBuilder.build();
    }

    public int size() {
        return lottos.size();
    }

    public List<Lotto> asList() {
        return List.copyOf(lottos);
    }
}
