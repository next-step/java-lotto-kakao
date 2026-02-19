package lotto.model;

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

    public LottoTicket buyManual(Wallet wallet, String numbers) {
        return manualSelector.buyTicket(wallet, numbers);
    }

    public LottoTickets buyAuto(Wallet wallet) {
        LottoTickets tickets = new LottoTickets();
        while (wallet.canAfford(LottoTicket.PRICE)) {
            tickets.insertTicket(autoMachine.issue(wallet));
        }
        return tickets;
    }
}
