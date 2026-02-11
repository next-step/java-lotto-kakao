package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatsBoard {

	private final GameScore gameScore;
	private final Map<WinLevel, Integer> matchedWinLevelCount;
	private final Integer ticketCount;

	public StatsBoard(GameScore gameScore, List<Ticket> tickets) {
		this.gameScore = gameScore;
		ticketCount = tickets.size();
		matchedWinLevelCount = new HashMap<>();

		for(WinLevel winLevel: WinLevel.getAll()) {
			matchedWinLevelCount.put(winLevel, 0);
		}

		for(Ticket ticket: tickets) {
			WinLevel level = validateTicket(ticket);
			matchedWinLevelCount.put(level, matchedWinLevelCount.getOrDefault(level, 0) + 1);
		}
	}

	public Integer getLevelCount(WinLevel winLevel) {
		return matchedWinLevelCount.get(winLevel);
	}

	WinLevel validateTicket(Ticket ticket) {
		if(gameScore == null) {
			throw new IllegalArgumentException("게임 정보가 없습니다!");
		}
		Long key = ticket.getKey();
		int winMatchcount = 0;
		for(var winNumber: gameScore.getWinNumbers()) {
			winMatchcount += (key & (1L <<winNumber)) == 0 ? 0 : 1;
		}
		Boolean bonousMatched = (key & (1L << gameScore.getBonusNumber())) > 0;
		return winLevelRouter(winMatchcount, bonousMatched);
	}

	// 핵심 비즈니스 로직 Winlevel의 FIRST / SECOND 등등이 뭘 의미 하는지 여기 담겨있음
	private WinLevel winLevelRouter(Integer winMatchCount, Boolean bonusMatched) {
		return WinLevel.make(winMatchCount, bonusMatched);
	}

	public Double getProfitRatio() {
		long ticketRevenue = 0L;
		long cost = 1000L * ticketCount;
		for(var zip: matchedWinLevelCount.entrySet())
			ticketRevenue += zip.getKey().getPrice() * zip.getValue();
		return (double)ticketRevenue / cost;
	}
}