package lotto;

import java.util.EnumMap;

public class GameResult {
    private EnumMap<LottoRank, Integer> gameResult;

    public GameResult() {
        gameResult = new EnumMap<>(LottoRank.class);
        for (LottoRank value : LottoRank.values()) {
            gameResult.put(value, 0);
        }
    }

    public void increaseOne(LottoRank lottoRank) {
        gameResult.merge(lottoRank, 1, Integer::sum);
    }

    // 로또 리스트들의 당첨금 총액 반환
    public long getLottoSum() {
        long sum = 0;
        for (LottoRank value : LottoRank.values()) {
            sum += value.getValue() * gameResult.get(value);
        }

        return sum;
    }

    // 수익률 반환
    public double getRateOfReturn() {
        double rate = (double) getLottoSum() / (getSize() * LottoPolicy.LOTTO_PRICE);
        // 100을 곱해서 반올림하고 다시 100.0으로 나눔
        return Math.floor(rate * 100) / 100.0;
    }

    public int getSize() {
        int size = 0;
        for (LottoRank value : LottoRank.values()) {
            size += gameResult.get(value);
        }

        return size;
    }

    // 특정 Enum(몇개 당첨인지) 개수 카운트
    public int countEnum(LottoRank lottoRank) {
        return gameResult.get(lottoRank);
    }
}
