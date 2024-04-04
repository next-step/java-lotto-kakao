package com.lotto.model;

public class LottoResult {
    private final LottoRank lottoRank;
    private int count;

    public LottoResult(LottoRank lottoRank) {
        this.lottoRank = lottoRank;
        this.count = 0;
    }

    public int getPrize() {
        return lottoRank.getPrize() * count;
    }

    public LottoRank getLottoRank() {
        return lottoRank;
    }

    public void addCount() {
        count++;
    }

    public boolean checkLottoRank(LottoRank lottoRank) {
        return this.lottoRank == lottoRank;
    }

    public boolean isFailed() {
        return lottoRank == LottoRank.FAIL;
    }

    public int getCount() {
        return count;
    }
}
