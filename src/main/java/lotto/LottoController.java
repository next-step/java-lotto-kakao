package lotto;

import lotto.model.*;
import lotto.model.dto.LottoResultDto;
import lotto.model.dto.LottoTicketDto;
import lotto.view.Input;
import lotto.view.Output;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoController {

    public static void play() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(Input.getPurchaseAmount());
        int manualPurchaseCount = Input.getManualPurchaseCount();
        LottoTickets lottoTickets = buyLottoTickets(purchaseAmount, manualPurchaseCount);

        Output.printPurchaseCount(manualPurchaseCount, lottoTickets.getSize() - manualPurchaseCount);
        Output.printLottoTickets(parseDto(lottoTickets));

        LottoTicket winningTicket = Input.getWinningTicket();
        LottoNumber bonusNumber = new LottoNumber(Input.getBonusNumber());
        WinningLottoTicket winningLottoTicket = new WinningLottoTicket(winningTicket, bonusNumber);

        LottoResult lottoResult = lottoTickets.makeWinningResult(winningLottoTicket);
        Output.printResult(new LottoResultDto(lottoResult.calculateReturnRate(purchaseAmount), lottoResult.makeLottoResultMap()));
    }

    private static List<LottoTicketDto> parseDto(LottoTickets lottoTickets) {
        return lottoTickets.getLottoTickets()
                .stream()
                .map(LottoTicketDto::new)
                .collect(Collectors.toList());
    }

    public static LottoTickets buyLottoTickets(PurchaseAmount purchaseAmount, int manualCount) {
        int count = (int) purchaseAmount.getPurchaseAmount() / LottoTicket.PRICE;
        int autoCount = count - manualCount;
        validatePurchaseCount(count, manualCount);
        LottoTickets lottoTickets = new LottoTickets(Input.getManualLottoTickets(manualCount));
        lottoTickets.add(LottoTicketGenerator.generate(autoCount));
        return lottoTickets;
    }

    private static void validatePurchaseCount(int count, int manualCount) {
        if (count < 1) {
            throw new IllegalArgumentException("구매 금액은 최소 1,000원 이상이어야 합니다.");
        }
        if (count < manualCount) {
            throw new IllegalArgumentException("수동으로 구매할 로또 수가 구매 금액보다 많습니다.");
        }
    }
}
