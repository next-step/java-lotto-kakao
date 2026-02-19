package lotto;

import static lotto.LottoNumberParser.parseLottoNumbers;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private List<Lotto> lottoList;

    public Lottos() {
        this.lottoList = new ArrayList<>();
    }

    public List<Lotto> getLottoList() {
        return List.copyOf(lottoList);
    }

    public void add(Lotto lotto) {
        this.lottoList.add(lotto);
    }

    public void setAllLottoResult(GameResult gameResult, WinningLotto winningLotto) {
        for (Lotto lotto : lottoList) {
            gameResult.increaseOne(lotto.evaluateRank(winningLotto));
        }
    }

    public void purchaseOneManualLotto(String input) {
        this.add(new Lotto(parseLottoNumbers(input)));
    }

    public void purchaseLotto(int count, LottoNumberStrategy strategy) {
        for (int i = 0; i < count; i++) {
            this.add(new Lotto(strategy.generate()));
        }
    }
}
