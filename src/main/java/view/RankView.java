package view;

import model.Rank;

public enum RankView {

    FIFTH(Rank.FIFTH, "3개 일치"),
    FOURTH(Rank.FOURTH, "4개 일치"),
    THIRD(Rank.THIRD, "5개 일치"),
    SECOND(Rank.SECOND, "5개 일치, 보너스 볼 일치"),
    FIRST(Rank.FIRST, "6개 일치");

    private final Rank rank;
    private final String description;

    RankView(Rank rank, String description) {
        this.rank = rank;
        this.description = description;
    }

    public Rank getRank() {
        return rank;
    }

    public String getDescription() {
        return description;
    }
}
