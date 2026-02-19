package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutoMachine {

    private final List<LottoNumber> numberList;

    public AutoMachine() {
        numberList = new ArrayList<>();
        for(int i=LottoNumber.START_NUMBER; i<=LottoNumber.END_NUMBER; i++){
            numberList.add(new LottoNumber(i));
        }
    }

    public LottoTicket issue(Wallet wallet) {
        wallet.spend(LottoTicket.PRICE);
        Collections.shuffle(numberList);
        return new LottoTicket(numberList.subList(0,LottoTicket.TICKET_SIZE));
    }

    public LottoTickets allIn(Wallet wallet) {

        LottoTickets lottoTickets = new LottoTickets(new ArrayList<>());
        while (wallet.canAfford(LottoTicket.PRICE)) {
            lottoTickets.add(this.issue(wallet));
        }
        return lottoTickets;
    }

}
