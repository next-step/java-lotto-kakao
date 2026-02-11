package service;

import domain.lotto.LottoFactory;
import domain.lotto.LottoGroup;
import domain.lotto.LottoIssuer;
import domain.winning.LottoResult;
import domain.winning.WinningLotto;

import java.util.List;

public class LottoService {

    public LottoService() {
    }

    public LottoGroup issueLottos(int purchaseAmount) {
        LottoIssuer lottoIssuer = new LottoIssuer(new LottoFactory());
        return lottoIssuer.issueAuto(purchaseAmount);
    }
}
