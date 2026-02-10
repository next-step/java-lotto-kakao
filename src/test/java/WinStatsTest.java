import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class WinStatsTest {
	GameInfo gameInfo = new GameInfo(7, Arrays.asList(1,2,3,4,5,6));
	@Test
	void HelloStats() {
		Ticket ticket = new Ticket(1,2,3,4,5,6);
		Integer bonusBall = 7;
		StatsService statsService = new StatsService();
		statsService.setGameInfo(gameInfo);
		Ticket[] tickets = {
			new Ticket(1,2,3,4,5,6),
			new Ticket(1,2,3,4,5,7),
			new Ticket(1,2,3,4,5,8),
			new Ticket(1,2,3,4,9,8),
			new Ticket(1,2,3,10,9,8),
			new Ticket(7,8,9,10,11,12),
		};

		WinLevel[] winLevels = {
			WinLevel.FIRST,
			WinLevel.SECOND,
			WinLevel.THIRD,
			WinLevel.FOURTH,
			WinLevel.FIFTH,
			WinLevel.LOSER,
		};

		for(int i = 0; i< 6; i++) {
			WinLevel winLevel = statsService.validateTicket(tickets[i]);
			assertThat(winLevel).isEqualTo(winLevels[i]);
		}
	}
	@Test
	void profitRatioMaker() {
		StatsService statsService = new StatsService();
		statsService.setGameInfo(gameInfo);
		List<Ticket> tickets = Arrays.asList(
			new Ticket(1,2,3,4,5,6),
			new Ticket(1,2,3,4,5,7),
			new Ticket(1,2,3,4,5,8),
			new Ticket(1,2,3,4,9,8),
			new Ticket(1,2,3,10,9,8),
			new Ticket(7,8,9,10,11,12)
		);
		Double profitRatio = statsService.getProfitRatio(tickets);
		assertThat(profitRatio).isEqualTo(338592.5);
	}
}
