package enumeration;

public enum LottoCondition {
    BEGIN(1),
    END(45),
    PRICE(1000),
    ;

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
