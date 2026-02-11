package lotto.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum WinningRank {

    NONE(0,1000,0,0),
    FIFTH(5000,5,3,0),
    FOURTH(50000,4,4, 0),
    THIRD(1_500_000,3,5, 0),
    SECOND(30_000_000,2,5,1),
    FIRST(2_000_000_000, 1, 6,0)
    ;

    public final Money winningPrice;
    public final int rank;
    private final int matchCount;
    private final int bonusCount;

    WinningRank(int winningPrice, int rank, int matchCount, int bonusCount) {
        this.winningPrice = new Money(winningPrice);
        this.rank = rank;
        this.matchCount = matchCount;
        this.bonusCount = bonusCount;
    }

    public boolean isSatisfied(int matchCount, int bonusCount) {
        return matchCount >= this.matchCount
                && bonusCount >= this.bonusCount;
    }

    public static WinningRank getRank(int matchCount, int bonusCount) {
        return Arrays.stream(values())
                .filter(rank -> rank.isSatisfied(matchCount, bonusCount))
                .min(Comparator.comparingInt(r -> r.rank))
                .orElse(NONE);
    }

    public String getInfoString(){
        StringBuilder sb =  new StringBuilder(matchCount+"개 일치");
        if(bonusCount !=0){
            sb.append(", 보너스 볼 일치");
        }
        sb.append(" ("+winningPrice.toString()+")");
        return sb.toString();
    }

    public static List<WinningRank> getValidRanks() {
        return Arrays.stream(values())
                .filter(rank -> rank.matchCount > 0)
                .sorted(Comparator.comparingInt(r -> -r.rank))
                .collect(Collectors.toList());
    }
}
