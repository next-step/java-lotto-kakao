package lotto.domain;

public class Price {

    public static final int LOTTO_PRICE = 1000;
    public static final String PRICE_UNDER_1000_ERROR = "1000원 미만의 구매 금액이 입력되었습니다.";

    private int price;

    public Price (int price) {
        validate(price);
        this.price = price;
    }

    private void validate(int price) {
        if (price < 1000) {
            throw new IllegalArgumentException(PRICE_UNDER_1000_ERROR);
        }
    }

    public int getPrice() {
        return price;
    }

    public int getLottoCount() {
        return this.price / LOTTO_PRICE;
    }
}
