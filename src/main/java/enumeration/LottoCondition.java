package enumeration;

public enum LottoCondition {
    BEGIN(1),
    END(45),

    PRICE(1000),

    FULL_MATCHED(6),
    SUBTLE_CRITERIA(2);

    private final int value;

    LottoCondition(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public static int range() {
        return END.value - BEGIN.value + 1;
    }
}
