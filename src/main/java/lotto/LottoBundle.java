package lotto;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class LottoBundle implements Iterable<Lotto>  {
    private final List<Lotto> lottos;
    public LottoBundle(List<Lotto> lottos) {
        this.lottos = List.copyOf(lottos);
    }

    public int size() {
        return lottos.size();
    }

    public LottoResult getLottoResult(WinningLotto winningLotto){
        return new LottoResult(lottos.stream()
                .map(winningLotto::judge)
                .collect(Collectors.toList()));
    }

    @Override
    public Iterator<Lotto> iterator() {
        return lottos.iterator();
    }

}
