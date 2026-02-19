package level1;

public class PurchaseAmount {

    private final int total;
    private final int manual;

    public PurchaseAmount(Price price, int manualCount) {
        int totalCalculated = price.value() / Constant.LOTTERY_PRICE;
        validate(totalCalculated, manualCount);
        this.total = totalCalculated;
        this.manual = manualCount;
    }

    private void validate(int total, int manual) {
        if (manual < 0) {
            throw new IllegalArgumentException("수량은 음수일 수 없습니다.");
        }
        if (manual > total) {
            throw new IllegalArgumentException("구매 가능 수량(" + total + "개)보다 많이 수동 구매할 수 없습니다.");
        }
    }

    public int getManual() {
        return manual;
    }

    public int getAuto() {
        return total - manual;
    }
}
