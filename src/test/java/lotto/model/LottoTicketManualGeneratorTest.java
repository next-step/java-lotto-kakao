package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LottoTicketManualGeneratorTest {
    @Test
    @DisplayName("로또 티켓이 원하는 숫자로 생성되는지 확인")
    void validateSame(){
        LottoTicketGenerator lottoTicketGenerator = new LottoTicketManualGenerator();

        List<Integer> manualNumbers = List.of(1,2,3,4,5,6);
        LottoTicket lottoTicket = lottoTicketGenerator.generate(manualNumbers);

        LottoTicket targetLottoTicket = new LottoTicket(
                manualNumbers.stream().map(LottoNumber::of).toList()
        );

        assertThat(lottoTicket.equals(targetLottoTicket)).isEqualTo(true);
    }

}
