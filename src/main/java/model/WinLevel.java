package model;

import java.util.List;

public enum WinLevel {

    FIRST, SECOND, THIRD, FOURTH, FIFTH, LOSER;

    public long getPrice() {
        if (this == WinLevel.FIRST) return 2_000_000_000L;
        if (this == WinLevel.SECOND) return 30_000_000L;
        if (this == WinLevel.THIRD) return 1_500_000L;
        if (this == WinLevel.FOURTH) return 50_000L;
        if (this == WinLevel.FIFTH) return 5_000L;
        return 0L;
    }

    public String getDescription() {
        if (this == WinLevel.FIRST) return "6개 일치";
        if (this == WinLevel.SECOND) return "5개 일치, 보너스 볼 일치";
        if (this == WinLevel.THIRD) return "5개 일치";
        if (this == WinLevel.FOURTH) return "4개 일치";
        if (this == WinLevel.FIFTH) return "3개 일치";
        return "당첨 되지 않았습니다.";
    }

    public static WinLevel make(int winMatchCount, boolean bonusMatched) {
        if (winMatchCount == 6) return WinLevel.FIRST;
        if (winMatchCount == 5 && bonusMatched) return WinLevel.SECOND;
        if (winMatchCount == 5) return WinLevel.THIRD;
        if (winMatchCount == 4) return WinLevel.FOURTH;
        if (winMatchCount == 3) return WinLevel.FIFTH;
        return WinLevel.LOSER;
    }

    public static List<WinLevel> getAll() {
        return List.of(
                WinLevel.FIFTH,
                WinLevel.FOURTH,
                WinLevel.THIRD,
                WinLevel.SECOND,
                WinLevel.FIRST,
                WinLevel.LOSER
        );
    }
}