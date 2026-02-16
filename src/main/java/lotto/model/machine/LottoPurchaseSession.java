package lotto.model.machine;

import lotto.model.common.Money;
import lotto.model.ticket.LottoTicket;
import lotto.model.ticket.TicketGeneratorCommand;

import java.util.ArrayList;
import java.util.List;

public class LottoPurchaseSession {

    private final Money deposit;
    private final LottoMachine lottoMachine;
    private Money totalPurchasedPrice;
    private final List<LottoTicket> lottoTickets;


    public LottoPurchaseSession(LottoMachine lottoMachine, Money depositMoney) {
        this.lottoMachine = lottoMachine;
        this.deposit = depositMoney;
        this.totalPurchasedPrice = new Money(0);
        this.lottoTickets = new ArrayList<>();
    }

    private Money remain(){
        return deposit.minus(totalPurchasedPrice);
    }

    public void purchase(TicketGeneratorCommand command) {
        lottoMachine.validatePurchasable(remain(),command.count());
        List<LottoTicket> generatedTickets = lottoMachine.generate(command);
        this.lottoTickets.addAll(generatedTickets);

        this.totalPurchasedPrice = this.totalPurchasedPrice.plus(
                lottoMachine.getPriceOfTickets(command.count())
        );
    }

    public int getPurchasableTicketCount() {
        Money leftMoney = deposit.minus(totalPurchasedPrice);
        return lottoMachine.getPurchasableTicketCount(leftMoney);
    }

    public LottoMachineGeneratedResult getResult() {
        return new LottoMachineGeneratedResult(this.totalPurchasedPrice,this.lottoTickets);
    }
}
