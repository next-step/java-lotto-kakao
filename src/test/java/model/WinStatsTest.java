package model;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class WinStatsTest {
	GameScore gameScore = new GameScore(7, Arrays.asList(1,2,3,4,5,6));
	@Test
	void ticketLevel() {
		Ticket ticket = new Ticket(1,2,3,4,5,6);
		Integer bonusBall = 7;
		List<Ticket> tickets =  Arrays.asList(
			new Ticket(1,2,3,4,5,6),
			new Ticket(1,2,3,4,5,7),
			new Ticket(1,2,3,4,5,8),
			new Ticket(1,2,3,4,9,8),
			new Ticket(1,2,3,10,9,8),
			new Ticket(7,8,9,10,11,12)
		);
		WinLevel[] winLevels = {
			WinLevel.FIRST,
			WinLevel.SECOND,
			WinLevel.THIRD,
			WinLevel.FOURTH,
			WinLevel.FIFTH,
			WinLevel.LOSER,
		};
		StatsBoard statsBoard = new StatsBoard(gameScore,tickets);


		for(int i = 0; i< 6; i++) {
			assertThat(tickets.get(i).getWinLevel(gameScore)).isEqualTo(winLevels[i]);
		}
	}

	@Test
	void profitRatioMaker() {
		StatsBoard statsBoard = new StatsBoard(gameScore,  Arrays.asList(
			new Ticket(1,2,3,4,5,6),
			new Ticket(1,2,3,4,5,7),
			new Ticket(1,2,3,4,5,8),
			new Ticket(1,2,3,4,9,8),
			new Ticket(1,2,3,10,9,8),
			new Ticket(7,8,9,10,11,12)
		));
		Double profitRatio = statsBoard.getProfitRatio();
		assertThat(profitRatio).isEqualTo(338592.5);
	}
}
