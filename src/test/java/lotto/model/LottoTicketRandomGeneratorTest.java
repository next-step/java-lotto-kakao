package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Random;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketRandomGeneratorTest{

	@Test
	@DisplayName("같은 시드에서 같은 결과값을 반환")
	void validateSameRandom() {
		Random random = new Random(20260105);
		Random targetRandom = new Random(20260105);

		LottoTicketRandomGenerator generator = new LottoTicketRandomGenerator();
		TicketRandomGeneratorCommand command = new TicketRandomGeneratorCommand(1,random);
		TicketRandomGeneratorCommand targetCommand = new TicketRandomGeneratorCommand(1,targetRandom);

		LottoTicket lottoTicket = generator.generate(command).getFirst();
		LottoTicket targetLottoTicket = generator.generate(targetCommand).getFirst();

		assertThat(lottoTicket.equals(targetLottoTicket)).isTrue();
	}

	@Test
	@DisplayName("다른 시드에서 다른 결과값을 반환")
	void validateDifferentRandom() {
		Random random = new Random(20260105);
		Random targetRandom = new Random(20260210);

		LottoTicketRandomGenerator generator = new LottoTicketRandomGenerator();
		TicketRandomGeneratorCommand command = new TicketRandomGeneratorCommand(1,random);
		TicketRandomGeneratorCommand targetCommand = new TicketRandomGeneratorCommand(1,targetRandom);

		LottoTicket lottoTicket = generator.generate(command).getFirst();
		LottoTicket targetLottoTicket = generator.generate(targetCommand).getFirst();

		assertThat(lottoTicket.equals(targetLottoTicket)).isFalse();
	}
}



