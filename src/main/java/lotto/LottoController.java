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
        Long manualPurchaseCount = Input.getManualPurchaseCount();
        LottoTickets lottoTickets = new LottoTickets(buyLottoTickets(purchaseAmount));

        Output.printPurchaseCount(lottoTickets.getSize());
        Output.printLottoTickets(parseDto(lottoTickets));

        LottoTicket winningTicket = parseWinningNumbers(Input.getWinningNumbers());
        LottoNumber bonusNumber = new LottoNumber(Input.getBonusNumber());
        WinningLottoTicket winningLottoTicket = new WinningLottoTicket(winningTicket, bonusNumber);

        LottoResult lottoResult = lottoTickets.makeWinningResult(winningLottoTicket);
        Output.printResult(new LottoResultDto(lottoResult.calculateReturnRate(purchaseAmount), lottoResult.makeLottoResultMap()));
    }

    private static LottoTicket parseWinningNumbers(String winningNumbers) {
        return Arrays.stream(winningNumbers.split(", "))
                .map(Integer::parseInt)
                .map(LottoNumber::new)
                .collect(Collectors.collectingAndThen(Collectors.toList(), LottoTicket::new));
    }

    private static List<LottoTicketDto> parseDto(LottoTickets lottoTickets) {
        return lottoTickets.getLottoTickets()
                .stream()
                .map(LottoTicketDto::new)
                .collect(Collectors.toList());
    }

    public static List<LottoTicket> buyLottoTickets(PurchaseAmount purchaseAmount) {
        int count = (int) purchaseAmount.getPurchaseAmount() / 1000;

        return LottoTicketGenerator.generate(count);
    }
}
