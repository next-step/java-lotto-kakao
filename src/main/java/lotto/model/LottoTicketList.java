package lotto.model;

import java.util.ArrayList;
import java.util.List;

public class LottoTicketList {
    private final List<LottoTicket> lottoTickets;

    public LottoTicketList(){
        this(new ArrayList<>());
    }

    public LottoTicketList(List<LottoTicket> lottoTickets) {
        if(lottoTickets == null) {
            lottoTickets = new ArrayList<>();
        }
        this.lottoTickets = lottoTickets;
    }

    public void insertTicket(LottoTicket lottoTicket) {
        lottoTickets.add(lottoTicket);
    }

    public WinningInfo cumulativeReport(WinningLotto winningLotto){
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
