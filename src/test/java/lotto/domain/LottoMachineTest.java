package lotto.domain;

import java.util.ArrayList;
import java.util.List;

import lotto.domain.dto.LottoResultDto;
import lotto.domain.dto.TicketDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class LottoMachineTest {

    List<Number> numberPool = new ArrayList<>();
    @BeforeEach
    void setUp() {
        for (int i = 1; i <= 45; i++) {
            numberPool.add(new Number(i));
        }
    }

    @Test
    public void 시스템은_구매_금액에_상응하는_개수의_로또_번호를_만들어준다() {
        LottoMachine lottoMachine = new LottoMachine(numberPool);
        int price = 15000;

        List<TicketDto> generatedTickets = lottoMachine.generateTickets(new Budget(price));
        Assertions.assertThat(generatedTickets.size()).isEqualTo(15);
    }

    @Test
    public void 시스템은_당첨번호를_받아_결과를_반환한다() {
        List<Number> fixedNumberPool = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            fixedNumberPool.add(new Number(i));
        }

        LottoMachine machine = new LottoMachine(fixedNumberPool);
        machine.generateTickets(new Budget(1000));

        WinningNumbers winningNumbers = new WinningNumbers(fixedNumberPool, new Number(7));

        LottoResultDto resultDto = machine.getResult(winningNumbers);

        int firstPrizeCount = resultDto.getLottoResult().get(Prize.FIRST);
        Assertions.assertThat(firstPrizeCount).isEqualTo(1);
    }
}
