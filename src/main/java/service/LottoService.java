package service;

import domain.lotto.LottoFactory;
import domain.lotto.LottoGroup;
import domain.lotto.LottoIssuer;
import domain.winning.LottoResult;
import domain.winning.WinningLotto;

import java.util.List;

public class LottoService {

    private final LottoIssuer lottoIssuer;

    public LottoService(LottoFactory lottoFactory) {
        this.lottoIssuer = new LottoIssuer(lottoFactory);
    }

    public LottoGroup issueAutoLottoGroup(int purchaseAmount) {
        return lottoIssuer.issueAuto(purchaseAmount);
    }

    public LottoGroup issueMixedLottoGroup(int totalPurchaseAmount, List<List<Integer>> manualNumbers) {
        return lottoIssuer.issueMixed(totalPurchaseAmount, manualNumbers);
    }

    public LottoResult calculateResult(LottoGroup lottoGroup, WinningLotto winningLotto) {
        return lottoGroup.compare(winningLotto);
    }
}
