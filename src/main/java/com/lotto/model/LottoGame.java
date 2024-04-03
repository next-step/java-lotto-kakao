package com.lotto.model;

import com.lotto.util.LottoGenerateStrategy;

public class LottoGame {
    private static final int LOTTO_PRICE = 1000;
    private final LottoTickets lottoTickets;
    private final LottoResults lottoResults;

    public LottoGame(int money, LottoGenerateStrategy lottoGenerateStrategy) {
        this.lottoTickets = new LottoTickets(money / LOTTO_PRICE, lottoGenerateStrategy);
        this.lottoResults = new LottoResults();
    }

    public LottoTickets getLottoTickets() {
        return lottoTickets;
    }

    public double calculateProfit() {
        double totalPrize = lottoResults.calculateTotalPrize();
        return totalPrize / (lottoTickets.size() * LOTTO_PRICE);
    }

    public LottoResults play(TargetLotto targetLotto) {
        lottoTickets.getLottoTickets().forEach(lottoTicket -> {
            LottoRank lottoRank = targetLotto.match(lottoTicket);
            lottoResults.addLottoRankCount(lottoRank);
        });
        return lottoResults;
    }
}
