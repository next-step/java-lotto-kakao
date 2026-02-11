package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutoMachine {

    private final List<LottoNumber> numberList;
    public static final Money LOTTO_PRICE = new Money(-1000);

    public AutoMachine() {
        numberList = new ArrayList<>();
        for(int i=LottoNumber.START_NUMBER; i<=LottoNumber.END_NUMBER; i++){
            numberList.add(new LottoNumber(i));
        }
    }

    public LottoTicket issue(Wallet wallet) {
        wallet.change(LOTTO_PRICE);
        Collections.shuffle(numberList);
        return new LottoTicket(numberList.subList(0,LottoTicket.TICKET_SIZE));
    }

    public LottoTicketList allIn(Wallet wallet) {

        LottoTicketList lottoTicketList = new LottoTicketList(new ArrayList<>());
        while (wallet.checkBalance(LOTTO_PRICE)) {
            lottoTicketList.insertTicket(this.issue(wallet));
        }
        return lottoTicketList;
    }
}
