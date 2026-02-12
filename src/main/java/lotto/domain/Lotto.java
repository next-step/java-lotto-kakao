package lotto.domain;

import lotto.util.LottoAutoCreator;

public class Lotto {
    private static final int PRICE = 1000;
    private final LottoBalls lottoBalls;

    public Lotto() {
        this.lottoBalls = LottoAutoCreator.lottoCreate();
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
