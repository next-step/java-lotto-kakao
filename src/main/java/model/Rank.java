package model;

public enum Rank {

    FIRST(2_000_000_000L, "6개 일치"),
    SECOND(30_000_000L, "5개 일치, 보너스 볼 일치"),
    THIRD(1_500_000L, "5개 일치"),
    FOURTH(50_000L, "4개 일치"),
    FIFTH(5_000L, "3개 일치"),
    LOSER(0L, "당첨 실패");

    private final long price;
    private final String description;

    Rank(long price, String description) {
        this.price = price;
        this.description = description;
    }

    public long getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public static Rank make(int matchCount, boolean bonus) {
        if (matchCount == 6) return Rank.FIRST;
        if (matchCount == 5 && bonus) return Rank.SECOND;
        if (matchCount == 5) return Rank.THIRD;
        if (matchCount == 4) return Rank.FOURTH;
        if (matchCount == 3) return Rank.FIFTH;
        return Rank.LOSER;
    }
}
