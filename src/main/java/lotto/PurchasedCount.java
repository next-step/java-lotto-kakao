package lotto;

public final class PurchasedCount {
    private final int manualCount;
    private final int autoCount;

    public PurchasedCount(int manual, int total) {
        validation(manual, total);
        this.manualCount = manual;
        this.autoCount = total - manual;
    }

    private void validation(int manual, int total){
        if(manual > total){
            throw new IllegalArgumentException();
        }
    }
}
