package com.lotto.model;

import com.lotto.util.LottoGenerateStrategy;

import java.util.List;

public class LottoGame {
    private static final int LOTTO_PRICE = 1000;
    private final LottoTickets lottoTickets;
    private final LottoResults lottoResults;

    public LottoGame(int money, LottoGenerateStrategy lottoGenerateStrategy) {
        validateMoney(money);
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

    public LottoResults play(List<Integer> winningNumbers, int bonusNumber) {
        TargetLotto targetLotto = new TargetLotto(winningNumbers, bonusNumber);
        List<LottoRank> lottoRanks = lottoTickets.matchAll(targetLotto);
        lottoResults.applyLottoRanks(lottoRanks);
        return lottoResults;
    }

    public int getLottoTicketSize() {
        return lottoTickets.size();
    }

    private void validateMoney(int money) {
        if (money < LOTTO_PRICE) {
            throw new IllegalArgumentException(String.format("로또 구입 금액은 최소 %s원입니다.", LOTTO_PRICE));
        }
    }
}
