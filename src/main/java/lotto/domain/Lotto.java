package lotto.domain;

import lotto.util.LottoAutoCreator;

public class Lotto {
    private final LottoBalls lottoBalls;
    private static final int PRICE = 1000;

    public Lotto() {
        this.lottoBalls = LottoAutoCreator.lottoAutoCreate();
    }

    public Lotto(Lotto lotto) {
        this.lottoBalls = new LottoBalls(lotto.lottoBalls);
    }

    public LottoBalls getLottoBallList() {
        return lottoBalls;
    }

    public String getLottoNumberString() {
        return lottoBalls.getLottoNumberString();
    }

    public static int getPrice() {
        return PRICE;
    }
}
