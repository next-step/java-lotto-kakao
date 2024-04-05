package lotto.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTickets {

    private final List<LottoTicket> lottoTickets;

    public LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = lottoTickets;
    }

    public LottoResult makeWinningResult(WinningLottoTicket winningTicket) {
        List<LottoRank> lottoRanks = lottoTickets.stream()
                .map(winningTicket::match)
                .collect(Collectors.toList());

        return new LottoResult(lottoRanks);
    }

    public int getSize() {
        return lottoTickets.size();
    }

    public List<LottoTicket> getLottoTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }
}
