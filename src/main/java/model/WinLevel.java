package model;

public enum WinLevel {
	FIRST, SECOND, THIRD, FOURTH, FIFTH, LOSER;

	public Long getPrice() {
		if(this == WinLevel.FIRST) return 2_000_000_000L;
		if(this == WinLevel.SECOND) return 30_000_000L;
		if(this == WinLevel.THIRD) return 1_500_000L;
		if(this == WinLevel.FOURTH) return 50_000L;
		if(this == WinLevel.FIFTH) return 5_000L;
		return 0L;
	}

}