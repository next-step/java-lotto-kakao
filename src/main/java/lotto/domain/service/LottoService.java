package lotto.domain.service;

import lotto.domain.LottoPlayer;
import lotto.domain.Money;

public interface LottoService {

    static final Money ONE_LOTTO_PRICE = Money.ONE_LOTTO_PRICE;
    static final String PRICE_NOT_ENOUGH_MSG = "구입금액은 1000원 이상이어야 합니다.";

    LottoPlayer createPlayer();
}
