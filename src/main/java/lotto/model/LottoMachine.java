package lotto.model;

import java.util.List;

public class LottoMachine {
	private final Money lottoTicketPrice;
	private final LottoTicketGeneratorRegistry registry;

	public LottoMachine(Money lottoTicketPrice, LottoTicketGeneratorRegistry registry) {
		this.lottoTicketPrice = lottoTicketPrice;
		this.registry = registry;

	}

	public int getPurchasableTicketCount(Money money){
		return money.divideBy(lottoTicketPrice);
	}

	public Money getPriceOfTickets(int count){
		return lottoTicketPrice.multiply(count);
	}

	public<C extends TicketGeneratorCommand> List<LottoTicket> generate(C command){
		LottoTicketGenerator<TicketGeneratorCommand> generator = registry.find(command);
		return generator.generate(command);
	}

	public void validatePurchasable(Money remainDeposit,int ticketCount){
		if (remainDeposit.isLessThan(lottoTicketPrice.multiply(ticketCount))){
			throw new IllegalArgumentException("티켓 생성을 위한 금액이 부족합니다.");
		}
	}
}
