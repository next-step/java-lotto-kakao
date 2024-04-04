package lotto.domain;

public enum Prize {
    NOTHING(0L, 999),
    FIFTH(5000L, 5),
    FOURTH(50000L, 4),
    THIRD(1500000L, 3),
    SECOND(30000000L, 2),
    FIRST(2000000000L, 1);

    private final Long reward;
    private final int order;

    Prize(Long reward, int order) {
        this.reward = reward;
        this.order = order;
    }

    public Long getReward() {
        return this.reward;
    }

    public int getOrder() {
        return this.order;
    }

    public static Prize evaluate(int matchCount, boolean isBonusNumberMatched) {
        if (matchCount <= 2) {
            return NOTHING;
        }
        if (matchCount == 3) {
            return FIFTH;
        }
        if (matchCount == 4) {
            return FOURTH;
        }
        if (matchCount == 5) {
            return evaluate(isBonusNumberMatched);
        }
        return FIRST;
    }

    private static Prize evaluate(boolean isBonusNumberMatched) {
        if (isBonusNumberMatched) {
            return SECOND;
        }
        return THIRD;
    }

}
