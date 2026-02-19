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
            throw new IllegalArgumentException("수동 구매 수는 전체 구매 수를 초과할 수 없습니다.");
        }
        if(manual < 0){
            throw new IllegalArgumentException("수동 구매 수는 0 이상이어야 합니다.");
        }
    }

    public int manualCount(){
        return this.manualCount;
    }

    public int autoCount(){
        return this.autoCount;
    }
}
