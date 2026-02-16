package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import lotto.config.LottoPolicy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LottoPurchaseSessionTest {
    private LottoMachine lottoMachine;
    private final Money lottoTicketPrice = new Money(LottoPolicy.LOTTO_TICKET_PRICE);

    @BeforeEach
    void setUp(){
        LottoTicketRandomGenerator randomGenerator = new LottoTicketRandomGenerator();
        LottoTicketManualGenerator manualGenerator = new LottoTicketManualGenerator();
        LottoTicketGeneratorRegistry registry = new LottoTicketGeneratorRegistry(List.of(
                randomGenerator,
                manualGenerator
        ));
        lottoMachine = new LottoMachine(lottoTicketPrice, registry);
    }

    @Test
    @DisplayName("넣은 금액보다 구매할 티겟 수가 초과하지 않아야함")
    void validatePurchasableTicketCount(){
        Money depositMoney = lottoTicketPrice.minus(new Money(1));

        LottoPurchaseSession lottoPurchaseSession = new LottoPurchaseSession(lottoMachine,depositMoney);

        TicketManualGeneratorCommand manualCommand = new TicketManualGeneratorCommand(List.of(List.of(1,2,3,4,5,6)));
        assertThatIllegalArgumentException().isThrownBy(()->{
            lottoPurchaseSession.purchase(manualCommand);
        });
    }

    @Test
    @DisplayName("구입 금액에 따라 수동, 자동 로또가 제대로 반환되는지 테스트")
    void validateLottoTicketsByDepositMoney(){
        Money depositMoney = new Money(4_000);
        int lottoTicketCount = depositMoney.divideBy(new Money(LottoPolicy.LOTTO_TICKET_PRICE));

        LottoPurchaseSession lottoPurchaseSession = new LottoPurchaseSession(lottoMachine,depositMoney);

        // 수동 1장, 자동 3장
        TicketManualGeneratorCommand manualCommand = new TicketManualGeneratorCommand(List.of(List.of(1,2,3,4,5,6)));
        lottoPurchaseSession.purchase(manualCommand);

        int purchasableTicketCount = lottoPurchaseSession.getPurchasableTicketCount();
        TicketRandomGeneratorCommand randomCommand = new TicketRandomGeneratorCommand(purchasableTicketCount);
        lottoPurchaseSession.purchase(randomCommand);

        LottoMachineGeneratedResult lottoMachineGeneratedResult = lottoPurchaseSession.getResult();
        assertThat(lottoMachineGeneratedResult.lottoTickets().size()).isEqualTo(lottoTicketCount);
    }
}
