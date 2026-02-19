package model;

import java.util.Map;
import java.util.List;

public class Buyers {
    private Lottos lottos;
    private Integer spentMoney = 0;
    private LottoResult lottoResult;
    private Result result;
    private Double profitRate = 0.0;
    private final Vendor vendor;

    public Buyers(){
        lottoResult = new LottoResult();
        lottos = new Lottos();
        vendor = new Vendor();
    }

    public Lottos buyLotto(Integer money) {
        Money purchaseMoney = new Money(money);
        Lottos returnedLottos = vendor.sell(purchaseMoney);
        setLottos(returnedLottos);
        addSpentMoney(purchaseMoney);
        return lottos;
    }

    public Lottos buyLotto(Integer money, Integer manualLottoCount, List<String> manualLottoInputs) {
        Money purchaseMoney = new Money(money);
        Lottos returnedLottos = vendor.sell(purchaseMoney, manualLottoCount, manualLottoInputs);
        setLottos(returnedLottos);
        addSpentMoney(purchaseMoney);
        return lottos;
    }

    public void setLottos(Lottos lottos) {
        this.lottos = lottos;
    }

    public void addSpentMoney(Integer money){
        addSpentMoney(new Money(money));
    }

    public void addSpentMoney(Money money){
        this.spentMoney += money.getValue();
    }

    public void setResult(Result result){
        this.result = result;
    }

    public Map<LottoRank, Integer> compare(){
        lottoResult = new LottoResult();
        if(result == null || spentMoney == 0){
            profitRate = 0.0;
            return lottoResult.getResultCount();
        }
        for(Lotto lotto : lottos.getLottoList()) lottoResult.add(calculateRank(lotto));
        profitRate = (double) lottoResult.calculateTotalPrize() / spentMoney;
        return lottoResult.getResultCount();
    }

    public Double getProfitRate(){
        return profitRate;
    }

    private LottoRank calculateRank(Lotto lotto){
        int matchCount = lotto.countMatches(result.getNumbers());
        boolean matchBonus = lotto.hasBonusNumber(result.getBonus());
        return LottoRank.valueOf(matchCount, matchBonus);
    }
}
