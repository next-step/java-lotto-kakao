public class Price {
    private final Integer value;
    private Integer lottoCount;

    private static final int PRICE_PER_LOTTO = 1000;

    public Price(Integer price) {
        value = price;
        lottoCount = value / PRICE_PER_LOTTO;
    }

    public Integer getLottoCount() {
        return lottoCount;
    }

    public Integer getValue() {
        return value;
    }

    public void subtractManualCount(int manualCount) {
        lottoCount -= manualCount;
    }
}
