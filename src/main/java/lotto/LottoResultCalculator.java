package lotto;

import java.util.*;

public class LottoResultCalculator {

    private final LottoPlayer lottoPlayer;
    private final WinningLotto winningLotto;
    private final Set<Integer> winningLottoSet;

    public LottoResultCalculator(LottoPlayer lottoPlayer, WinningLotto winningLotto) {
        this.lottoPlayer = lottoPlayer;
        this.winningLotto = winningLotto;
        this.winningLottoSet = new HashSet<>(winningLotto.getLotto().getNumbers());
    }

    public LottoResult calculate() {
        HashMap<LottoStatus, Integer> gameResultMap = getGameStatusIntegerHashMap();
        long profit = calculateProfit(gameResultMap);
        double profitRate = calculateProfitRate(profit);

        return new LottoResult(gameResultMap, profit, profitRate);
    }

    private HashMap<LottoStatus, Integer> getGameStatusIntegerHashMap() {
        HashMap<LottoStatus, Integer> gameResultMap = new HashMap<>();

        for (Lotto userLotto : lottoPlayer.getLottos()) {
            Set<Integer> userSet = new HashSet<>(userLotto.getNumbers());
            userSet.retainAll(winningLottoSet);
            int count = userSet.size();
            boolean hasBonus = checkBonusNumber(userLotto);
            LottoStatus lottoStatus = LottoStatus.judgeGameStatus(count, hasBonus);

            gameResultMap.put(lottoStatus, gameResultMap.getOrDefault(lottoStatus, 0) + 1);
        }
        return gameResultMap;
    }

    private double calculateProfitRate(long profit) {
        return (double) profit / lottoPlayer.getPrice();
    }

    private long calculateProfit(Map<LottoStatus, Integer> map) {
        long profit = 0;
        for (LottoStatus lottoStatus : map.keySet()) {
            profit += lottoStatus.getPrice() * map.get(lottoStatus);
        }

        return profit;
    }

    private boolean checkBonusNumber(Lotto userLotto) {
        Set<Integer> userLottoSet = new HashSet<>(userLotto.getNumbers());
        return userLottoSet.contains(winningLotto.getBonusNumber());
    }
}
