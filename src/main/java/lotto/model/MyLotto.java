package lotto.model;

public class MyLotto {
    private final LottoTickets tickets;

    public MyLotto() {
        this.tickets = new LottoTickets();
    }

    public void addTickets(LottoTickets newTickets) {
        tickets.addAll(newTickets);
    }

    public LottoTickets getAllTickets() {
        return tickets;
    }

    public WinningInfo calculateResult(WinningLotto winningLotto) {
        return tickets.winningResult(winningLotto);
    }
}