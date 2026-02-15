package lotto.domain.service;

import lotto.domain.LottoPlayer;

public interface LottoService {

    static final int ONE_LOTTO_PRICE = 1000;
    static final String PRICE_NOT_ENOUGH_MSG = "구입금액은 1000원 이상이어야 합니다.";

    LottoPlayer createPlayer();
}
