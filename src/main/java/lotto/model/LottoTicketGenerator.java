package lotto.model;

import java.util.List;


public interface LottoTicketGenerator<C extends TicketGeneratorCommand> {
    Class<C> commandType();
    List<LottoTicket> generate(C command);
}
