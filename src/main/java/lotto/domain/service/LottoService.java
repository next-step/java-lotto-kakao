package lotto.domain.service;

import lotto.domain.Lotto;
import lotto.domain.LottoPlayer;
import lotto.domain.Money;

import java.util.List;

public interface LottoService {

    LottoPlayer purchase(Money price, List<Lotto> manualLottos);
}
