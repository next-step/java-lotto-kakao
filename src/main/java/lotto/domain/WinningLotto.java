package lotto.domain;

import java.util.*;

public class WinningLotto {

    public static final String BONUS_NUMBER_DUPLICATE_EXCEPTION = "당첨번호와 중복된 숫자를 보너스 번호로 등록할 수 없습니다.";

    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto lotto, LottoNumber bonusNumber) {
        validateDuplicate(lotto, bonusNumber);
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    private void validateDuplicate(Lotto lotto, LottoNumber bonusNumber) {
        if (lotto.getNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException(BONUS_NUMBER_DUPLICATE_EXCEPTION);
        }
    }

    public Lotto getLotto() {
        return lotto;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }

    public LottoResult calculate(List<Lotto> userLottos, int price) {
        Map<LottoStatus, Integer> lottoResultMap = getLottoResultMap(userLottos);
        long profit = calculateProfit(lottoResultMap);
        double profitRate = calculateProfitRate(profit, price);

        return new LottoResult(lottoResultMap, profit, profitRate);
    }

    private Map<LottoStatus, Integer> getLottoResultMap(List<Lotto> userLottos) {
        HashMap<LottoStatus, Integer> lottoResultMap = new HashMap<>();

        for (Lotto userLotto : userLottos) {
            int count = this.lotto.matchCount(userLotto);
            boolean hasBonus = checkBonusNumber(userLotto);
            LottoStatus lottoStatus = LottoStatus.judgeGameStatus(count, hasBonus);
            lottoResultMap.put(lottoStatus, lottoResultMap.getOrDefault(lottoStatus, 0) + 1);
        }
        return lottoResultMap;
    }

    private boolean checkBonusNumber(Lotto userLotto) {
        Set<LottoNumber> userLottoSet = new HashSet<>(userLotto.getNumbers());
        return userLottoSet.contains(this.bonusNumber);
    }

    private long calculateProfit(Map<LottoStatus, Integer> map) {
        long profit = 0;
        for (LottoStatus gameStatus : map.keySet()) {
            profit += gameStatus.getPrice() * map.get(gameStatus);
        }

        return profit;
    }

    private double calculateProfitRate(long profit, int price) {
        return (double) profit / price;
    }
}
