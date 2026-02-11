package lotto;

import java.util.List;
import java.util.stream.Collectors;

public class LottoBundle {
    private final List<Lotto> lottos;
    public LottoBundle(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public int size() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public LottoResult getLottoResult(WinningLotto winningLotto){
        return new LottoResult(lottos.stream()
                .map(lotto -> LottoJudge.judge(winningLotto, lotto))
                .collect(Collectors.toList()));
    }

}
