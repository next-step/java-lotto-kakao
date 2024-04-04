package com.lotto.view;

import com.lotto.model.*;

import java.util.Collections;
import java.util.List;

public class LottoGameOutputView {

    public void printLottoResults(LottoResults lottoResults) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
        List<LottoResult> reversedLottoResults = getReversedLottoResults(lottoResults);
        reversedLottoResults.stream()
                .filter(result -> !result.isFailed())
                .forEach(this::printLottoResult);
    }

    public void printLottoTicketSize(int lottoCount) {
        System.out.println(lottoCount + "개를 구매했습니다.");
    }

    public void printLottoTicketList(LottoGame lottoGame) {
        LottoTickets lottoTickets = lottoGame.getLottoTickets();
        lottoTickets.getLottoTickets().forEach(System.out::println);
    }

    public void printProfitRate(LottoGame lottoGame) {
        System.out.println("총 수익률은 " + String.format("%.02f", lottoGame.calculateProfit()) + "입니다.");
    }

    private void printLottoResult(LottoResult lottoResult) {
        LottoRank lottoRank = lottoResult.getLottoRank();
        String lottoRankString = lottoRankToString(lottoRank);
        System.out.println(lottoRankString + lottoResult.getCount() + "개");
    }

    private String lottoRankToString(LottoRank lottoRank) {
        int matchCount = lottoRank.getMatchCount();
        boolean bonus = lottoRank.isBonus();
        int prize = lottoRank.getPrize();

        String prefix = matchCount + "개 일치";
        if (bonus) {
            prefix += ", 보너스 볼 일치";
        }

        return (prefix + " (" + prize + "원)- ");
    }

    private List<LottoResult> getReversedLottoResults(LottoResults lottoResults) {
        List<LottoResult> lottoResult = lottoResults.getLottoResults();
        Collections.reverse(lottoResult);
        return lottoResult;
    }
}
