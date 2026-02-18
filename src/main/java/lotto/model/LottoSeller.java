package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoSeller {
    public static final Money LOTTO_PRICE = new Money(1000);
    private final List<LottoNumber> numberList;

    public LottoSeller() {
        numberList = new ArrayList<>();
        for (int i = LottoNumber.START_NUMBER; i <= LottoNumber.END_NUMBER; i++) {
            numberList.add(new LottoNumber(i));
        }
    }

    private LottoTicket issue() {
        Collections.shuffle(numberList);
        return new LottoTicket(numberList.subList(0, LottoTicket.TICKET_SIZE));
    }

    public LottoTickets sellAuto(Wallet wallet) {
        LottoTickets lottoTicketList = new LottoTickets(new ArrayList<>());
        while (wallet.checkBalance(LOTTO_PRICE)) {
            wallet.pay(LOTTO_PRICE);
            lottoTicketList.insertTicket(this.issue());
        }
        return lottoTicketList;
    }

    public void checkPurchasability(Wallet wallet, int count) {
        Money price = LOTTO_PRICE.multiple(count);
        if (!wallet.checkBalance(price)) {
            throw new IllegalArgumentException("수동 구매 금액이 보유 금액보다 클 수 없습니다.");
        }
    }

    public void sellManual(Wallet wallet, LottoTickets manualTickets) {
        Money price = LOTTO_PRICE.multiple(manualTickets.size());
        if (!wallet.checkBalance(price)) {
            throw new IllegalArgumentException("수동 구매 금액이 보유 금액보다 클 수 없습니다.");
        }
        wallet.pay(price);
    }
}