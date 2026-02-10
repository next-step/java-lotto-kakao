package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatsBoard {

	private final GameInfo gameInfo;
	private final Map<WinLevel, Integer> matchedWinLevelCount;
	private final Integer ticketCount;
	StatsBoard(GameInfo gameInfo, List<Ticket> tickets) {
		this.gameInfo = gameInfo;
		ticketCount = tickets.size();
		matchedWinLevelCount = new HashMap<>();
		for(Ticket ticket: tickets) {
			WinLevel level = validateTicket(ticket);
			matchedWinLevelCount.put(level, matchedWinLevelCount.getOrDefault(level, 0) + 1);
		}
	}

	WinLevel validateTicket(Ticket ticket) {
		if(gameInfo == null) {
			throw new IllegalArgumentException("게임 정보가 없습니다!");
		}
		Long key = ticket.getKey();
		Integer winMatchcount = 0;
		for(var winNumber: gameInfo.winNumbers) {
			winMatchcount += (key & (1L <<winNumber)) == 0 ? 0 : 1;
		}
		Boolean bonousMatched = (key & (1L <<gameInfo.bonusNumber)) > 0;
		return winLevelRouter(winMatchcount, bonousMatched);
	}

	private WinLevel winLevelRouter(Integer winMatchCount, Boolean bonusMatched) {
		if(winMatchCount == 6) return WinLevel.FIRST;
		if(winMatchCount == 5 && bonusMatched) return WinLevel.SECOND;
		if(winMatchCount == 5) return WinLevel.THIRD;
		if(winMatchCount == 4) return WinLevel.FOURTH;
		if(winMatchCount == 3) return WinLevel.FIFTH;
		return WinLevel.LOSER;
	}

	public Double getProfitRatio() {
		long ticketRevenue = 0L;
		long cost = 1000L * ticketCount;
		for(var zip: matchedWinLevelCount.entrySet())
			ticketRevenue += zip.getKey().getPrice() * zip.getValue();
		return (double)ticketRevenue / cost;
	}
}