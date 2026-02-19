package lotto.model.ticket;

import java.util.List;

public class LottoTicketManualGenerator implements LottoTicketGenerator<TicketManualGeneratorCommand> {
    @Override
    public Class<TicketManualGeneratorCommand> commandType(){
        return TicketManualGeneratorCommand.class;
    }

    private LottoTicket generateTicket(List<LottoNumber> manualNumbers){
        return new LottoTicket(manualNumbers);
    }

    public List<LottoTicket> generate(TicketManualGeneratorCommand command){
        return command.numbers().stream().map(this::generateTicket).toList();
    }
}
