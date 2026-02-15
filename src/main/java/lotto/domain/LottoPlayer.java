package lotto.domain;

public class LottoPlayer {

    public static final int LOTTO_PRICE = 1000;
    public static final String PRICE_TOO_LOW_FAIL_MSG = "1000원 미만의 구매 금액이 입력되었습니다.";

    private int price;
    private Lottos lottos;

    public LottoPlayer(int price, Lottos lottos) {
        validatePrice(price);
        this.price = price;
        this.lottos = lottos;
    }

    public static LottoPlayer of(int price, Lottos lottos) {
        return new LottoPlayer(price, lottos);
    }

    private void validatePrice(int price) {
        if (price < LOTTO_PRICE) {
            throw new IllegalArgumentException(PRICE_TOO_LOW_FAIL_MSG);
        }
    }

    public int getPrice() {
        return price;
    }

    public int getLottoCount() {
        return lottos.size();
    }

    public Lottos getLottos() {
        return lottos;
    }
}
