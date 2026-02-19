package lotto.model.machine;

import lotto.model.common.Money;
import lotto.model.ticket.LottoTicket;

import java.util.List;

public record LottoMachineGeneratedResult(
		Money totalPrice,
		List<LottoTicket> lottoTickets
) {
}
