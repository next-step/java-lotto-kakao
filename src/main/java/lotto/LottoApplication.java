package lotto;

import lotto.config.LottoPolicy;
import lotto.controller.LottoController;
import lotto.model.common.Money;
import lotto.model.machine.LottoMachine;
import lotto.model.ticket.LottoTicketGeneratorRegistry;
import lotto.model.ticket.LottoTicketManualGenerator;
import lotto.model.ticket.LottoTicketRandomGenerator;

import java.util.List;

public class LottoApplication {

	public static void main(String[] args) {
		LottoTicketRandomGenerator lottoTicketRandomGenerator = new LottoTicketRandomGenerator();
		LottoTicketManualGenerator lottoTicketManualGenerator = new LottoTicketManualGenerator();
		LottoTicketGeneratorRegistry lottoTicketGeneratorRegistry = new LottoTicketGeneratorRegistry(
				List.of(
					lottoTicketManualGenerator,
					lottoTicketRandomGenerator
				)
		);
		LottoMachine lottoMachine = new LottoMachine(new Money(LottoPolicy.LOTTO_TICKET_PRICE), lottoTicketGeneratorRegistry);
		LottoController lottoController = new LottoController(lottoMachine);

		lottoController.run();
	}
}
