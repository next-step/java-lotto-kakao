package lotto.view;

import lotto.model.LottoRank;
import lotto.model.dto.LottoResultDto;
import lotto.model.dto.LottoTicketDto;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Output {

    public static void printPurchaseCount(int manualPurchaseCount, int autoPurchaseCount) {
        System.out.println("수동으로 " + manualPurchaseCount + "장, 자동으로 " + + autoPurchaseCount + " 장을 구매했습니다.");
    }

    private Output() {
    }

    public static void printLottoTickets(List<LottoTicketDto> lottoTickets) {
        lottoTickets.forEach(Output::printLottoTicket);
        System.out.println();
    }

    public static void printResult(LottoResultDto lottoResultDto) {
        System.out.println("\n당첨 통계");
        System.out.println("---------");
        lottoResultDto.getLottoResult().entrySet().stream()
                .sorted(Comparator.comparingInt(o -> o.getKey().getPrize()))
                .forEach(entry -> printLottoRank(entry.getKey(), entry.getValue()));
        System.out.println("총 수익률은 " + lottoResultDto.getReturnRate() + "입니다.");
    }

    private static void printLottoRank(LottoRank lottoRank, int count) {
        System.out.println(lottoRank.getMatchCount() + "개 일치" + (lottoRank.isMatchBonus() ? ", 보너스 볼 일치" : "")
                + "(" + lottoRank.getPrize() + ")- " + count + "개");
    }

    private static void printLottoTicket(LottoTicketDto lottoTicket) {
        System.out.println("[" + lottoTicket.getLottoNumbers().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "))
                + "]");
    }
}
