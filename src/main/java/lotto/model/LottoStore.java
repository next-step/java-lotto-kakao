package lotto.model;

import java.util.List;

public class LottoStore {

    private final ManualSelector manualSelector;
    private final AutoMachine autoMachine;

    public LottoStore() {
        this.manualSelector = new ManualSelector();
        this.autoMachine = new AutoMachine();
    }

    public int getMaxPurchasable(Money money) {
        return money.division(LottoTicket.PRICE).intValue();
    }

    public void validateManualCount(Money money, int manualCount) {
        int maxPurchasable = getMaxPurchasable(money);
        if (manualCount > maxPurchasable) {
            throw new IllegalArgumentException("구매 금액으로 " + maxPurchasable + "장까지만 구매 가능합니다.");
        }
    }

    public LottoTickets buyManual(Wallet wallet, List<String> numbersList) {
        List<LottoTicket> list = numbersList.stream()
                .map(nums -> manualSelector.buyTicket(wallet, nums))
                .toList();
        return new LottoTickets(list);
    }

    public LottoTickets buyAutoAllIn(Wallet wallet) {
        return autoMachine.allIn(wallet);
    }
}
