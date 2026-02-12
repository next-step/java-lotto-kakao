package model;

public enum Rank {

    FIRST(2_000_000_000L),
    SECOND(30_000_000L),
    THIRD(1_500_000L),
    FOURTH(50_000L),
    FIFTH(5_000L),
    LOSER(0L);

    private final long price;

    Rank(long price) {
        this.price = price;
    }

    public long getPrice() {
        return price;
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
