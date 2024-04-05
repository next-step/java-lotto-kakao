package lotto;

import lotto.model.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTicketsTest {

    @Test
    void 로또_티켓을_담는다() {

        List<LottoTicket> lottoTicketList = List.of(
                createLottoTicket(1, 2, 3, 4, 5, 6),
                createLottoTicket(1, 2, 3, 4, 5, 7)
        );
        LottoTickets lottoTickets = new LottoTickets(lottoTicketList);

        assertThat(lottoTickets.getSize()).isEqualTo(lottoTicketList.size());
    }

    @Test
    void 로또_당첨_결과를_반환한다() {
        WinningLottoTicket winningLottoTicket = new WinningLottoTicket(
                createLottoTicket(1, 2, 3, 4, 5, 6),
                new LottoNumber(7)
        );
        LottoTickets lottoTickets = new LottoTickets(List.of(
                createLottoTicket(1, 2, 3, 4, 5, 6),
                createLottoTicket(1, 2, 3, 4, 5, 7)
        ));
        LottoResult lottoResult = lottoTickets.makeWinningResult(winningLottoTicket);

        assertThat(lottoResult.getLottoRanks()).containsExactly(LottoRank.FIRST, LottoRank.SECOND);
    }

    @Test
    void add는_기존_로또티켓들에_새로운_로또티켓을_더한다() {
        LottoTickets lottoTickets = new LottoTickets(List.of(
                createLottoTicket(1, 2, 3, 4, 5, 6),
                createLottoTicket(1, 2, 3, 4, 5, 7)
        ));
        List<LottoTicket> additionalLottoTickets = List.of(
                createLottoTicket(1, 2, 3, 4, 5, 8),
                createLottoTicket(1, 2, 3, 4, 5, 9)
        );

        int totalCount = lottoTickets.getSize() + additionalLottoTickets.size();
        lottoTickets.add(additionalLottoTickets);

        assertThat(lottoTickets.getSize()).isEqualTo(totalCount);
        assertThat(lottoTickets.getLottoTickets()).containsAll(additionalLottoTickets);
    }

    private LottoTicket createLottoTicket(int... numbers) {
        return Arrays.stream(numbers)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.collectingAndThen(Collectors.toList(), LottoTicket::new));
    }
}
