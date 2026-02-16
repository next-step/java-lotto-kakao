package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.model.ticket.LottoNumber;
import lotto.model.ticket.LottoTicket;
import lotto.model.ticket.LottoTicketManualGenerator;
import lotto.model.ticket.TicketManualGeneratorCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LottoTicketManualGeneratorTest {
    @Test
    @DisplayName("로또 티켓이 원하는 숫자로 생성되는지 확인")
    void validateSame(){
        List<List<Integer>> manualNumbers = List.of(List.of(1,2,3,4,5,6));

        LottoTicketManualGenerator lottoTicketGenerator = new LottoTicketManualGenerator();
        TicketManualGeneratorCommand command = new TicketManualGeneratorCommand(manualNumbers);

        LottoTicket lottoTicket = lottoTicketGenerator.generate(command).getFirst();

        LottoTicket targetLottoTicket = new LottoTicket(
                manualNumbers.getFirst().stream().map(LottoNumber::of).toList()
        );

        assertThat(lottoTicket.equals(targetLottoTicket)).isEqualTo(true);
    }

}
