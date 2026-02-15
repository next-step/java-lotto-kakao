package lotto;

import java.util.ArrayList;
import java.util.List;

public class PurchasedLottoBundle {
    private LottoBundle manualBundle;
    private LottoBundle autoBundle;

    public PurchasedLottoBundle(LottoBundle manualBundle, LottoBundle autoBundle) {
        this.manualBundle = manualBundle;
        this.autoBundle = autoBundle;
    }

    public LottoResult makeLottoResult(WinningLotto winningLotto){
        List<Rank> manualRankList = manualBundle.makeLottoRanks(winningLotto);
        List<Rank> autoRankList = autoBundle.makeLottoRanks(winningLotto);

        List<Rank> ranks = new ArrayList<>(manualRankList);
        ranks.addAll(autoRankList);

        return new LottoResult(ranks);
    }
}
