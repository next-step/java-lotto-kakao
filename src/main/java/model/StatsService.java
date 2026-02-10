package model;

import java.util.List;

public class StatsService {

	private GameInfo gameInfo;


	public void setGameInfo(GameInfo info) {
		gameInfo = info;
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

	public Double getProfitRatio(List<Ticket> tickets) {
		long ticketRevenue = 0L;
		long cost = 1000L * tickets.size();
		for(Ticket ticket: tickets)
			ticketRevenue += this.validateTicket(ticket).getPrice();
		return (double)ticketRevenue / cost;
	}
}