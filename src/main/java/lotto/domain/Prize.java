package lotto.domain;

public enum Prize {
    NOTHING(0L, 2, 999 ),
    FIFTH(5000L, 3, 5),
    FOURTH(50000L, 4, 4),
    THIRD(1500000L, 5, 3),
    SECOND(30000000L, 5, 2),
    FIRST(2000000000L, 6, 1);

    private final Long reward;
    private final int matchCount;
    private final int order;

    Prize(Long reward, int matchCount, int order) {
        this.reward = reward;
        this.matchCount = matchCount;
        this.order = order;
    }

    public Long totalReward(Long count) {
        return this.reward * count;
    }

    public int getOrder() {
        return order;
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

    @Override
    public String toString() {
        if (this.matchCount <= 2) {
            return "낙첨";
        }

        StringBuilder sb = new StringBuilder(this.matchCount + "개 일치");
        if (this == SECOND) {
            sb.append(", 보너스 볼 일치");
        }
        sb.append("(").append(this.reward).append("원)");

        return sb.toString();
    }
}
