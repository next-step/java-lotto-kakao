package service;

import domain.lotto.*;

import java.util.List;

public class LottoService {

    public LottoGroup issue(Purchase purchase) {
        return new LottoGroup(issueManualLottos(purchase), issueAutoLottos(purchase));
    }

    private List<Lotto> issueManualLottos(Purchase purchase) {
        return new ManualLottoFactory(purchase.getManualLottosNumbers()).create();
    }

    private List<Lotto> issueAutoLottos(Purchase purchase) {
        return new AutoLottoFactory(purchase.getAutoLottoCount()).create();
    }
}
