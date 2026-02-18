package lotto.model;

import java.util.ArrayList;
import java.util.List;

public class LottoTickets {
    private final List<LottoTicket> lottoTickets;

    public LottoTickets(){
        this(new ArrayList<>());
    }

    public LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = lottoTickets;
    }

    public void insertTicket(LottoTicket lottoTicket) {
        lottoTickets.add(lottoTicket);
    }

    public void addAll(LottoTickets other) {
        this.lottoTickets.addAll(other.lottoTickets);
    }

    public WinningInfo winningResult(WinningLotto winningLotto){
        WinningInfo winningInfo = new WinningInfo();
        lottoTickets.forEach(ticket -> winningInfo.addResult(winningLotto.checkRank(ticket)));
        return winningInfo;
    }

    public int size() {
        return lottoTickets.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (LottoTicket ticket : lottoTickets) {
            sb.append(ticket.toString()).append("\n");
        }
        return sb.toString().trim();
    }
}
