package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;
import java.util.Random;

import lotto.model.common.Money;
import lotto.model.machine.LottoMachine;
import lotto.model.ticket.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoMachineTest {

	private LottoMachine lottoMachine;
	private final Money ticketPrice = LottoMachine.LOTTO_TICKET_PRICE;
	@BeforeEach
	void setup(){
		LottoTicketGeneratorRegistry registry = new LottoTicketGeneratorRegistry(
				List.of(
						new LottoTicketManualGenerator(),
						new LottoTicketRandomGenerator()
				)
		);

		lottoMachine = new LottoMachine(registry);
	}

	@Test
	@DisplayName("원하는 티켓 발행 개수대로 티켓이 뽑히는지 확인")
	void validateLottoTicketCountByPurchasePrice() {
		TicketRandomGeneratorCommand command = new TicketRandomGeneratorCommand(14,new Random());
		List<LottoTicket> tickets = lottoMachine.generate(command);

		assertThat(tickets.size()).isEqualTo(14);
	}

	@Test
	@DisplayName("티켓 최소 구매 금액 미만 예외 처리")
	void validateMinimumPurchasePrice() {
		Money purchasePrice = ticketPrice.minus(new Money(1));

		assertThatIllegalArgumentException().isThrownBy(() -> lottoMachine.validatePurchasable(purchasePrice, 1)
		);
	}
}
