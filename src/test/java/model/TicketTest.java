package model;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TicketTest {

	private final LotteryWinningNumbers lotteryWinningNumbers =
		new LotteryWinningNumbers(7, Arrays.asList(1, 2, 3, 4, 5, 6));

	@Test
	void ticketNumberCompared() {
		Ticket leftTicket = new Ticket(1,2,3,4,5,6);
		Ticket rightTicket = new Ticket(6,5,4,3,2,1);
		assertThat(leftTicket.getNumbers()).isEqualTo(rightTicket.getNumbers());
	}

	@DisplayName("5개 일치 + 보너스 일치면 2등")
	@Test
	void secondLevelByFiveMatchesAndBonus() {
		Ticket ticket = new Ticket(1, 2, 3, 4, 5, 7);
		assertThat(ticket.getWinLevel(lotteryWinningNumbers)).isEqualTo(WinLevel.SECOND);
	}

	@DisplayName("5개 일치 + 보너스 불일치면 3등")
	@Test
	void thirdLevelByFiveMatchesWithoutBonus() {
		Ticket ticket = new Ticket(1, 2, 3, 4, 5, 8);
		assertThat(ticket.getWinLevel(lotteryWinningNumbers)).isEqualTo(WinLevel.THIRD);
	}

	@DisplayName("각 매칭 개수에 따라 올바른 등수를 반환")
	@ParameterizedTest(name = "{index}: {0} -> {1}")
	@MethodSource("ticketsAndExpectedLevels")
	void ticketWinLevel(Ticket ticket, WinLevel expectedLevel) {
		assertThat(ticket.getWinLevel(lotteryWinningNumbers)).isEqualTo(expectedLevel);
	}

	private static Stream<Arguments> ticketsAndExpectedLevels() {
		return Stream.of(
			Arguments.of(new Ticket(1, 2, 3, 4, 5, 6), WinLevel.FIRST),
			Arguments.of(new Ticket(1, 2, 3, 4, 9, 8), WinLevel.FOURTH),
			Arguments.of(new Ticket(1, 2, 3, 10, 9, 8), WinLevel.FIFTH),
			Arguments.of(new Ticket(8, 9, 10, 11, 12, 13), WinLevel.LOSER)
		);
	}
}