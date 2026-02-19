package lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoGame {
    private WinningLotto winningLotto;
    private Lottos lottos;

    public LottoGame() {
        lottos = new Lottos();
    }

    public Lottos getLottos() {
        return lottos;
    }

    // 구매 가능한 갯수 반환
    public int calculateTotalLottoCount(int money) {
        validatePurchaseMoneyRange(money);
        return money / LottoPolicy.LOTTO_PRICE;
    }

    public int calculateManualLottoCount(int totalCount, int manualCount) {
        validatePurchaseMoneyRange(totalCount, manualCount);
        return manualCount;
    }

    // 로또 구매
    public void purchaseLotto(int count, LottoNumberStrategy strategy) {
        lottos.purchaseLotto(count, strategy);
    }

    public void purchaseManualLotto(String input) {
        lottos.purchaseOneManualLotto(input);
    }

    // 구매한 로또 출력
    public List<List<Integer>> getLottoListAsList() {
        List<List<Integer>> list = new ArrayList<>();

        for (Lotto lotto : lottos.getLottoList()) {
            list.add(lotto.getLottoNumbersAsList());
        }

        return list;
    }

    // 당첨 로또 생성
    public void createWinningLotto(String input, String bonus) {
        winningLotto = new WinningLotto(input, bonus);
    }

    // 모든 로또 결과 설정
    public GameResult generateGameResult() {
        if (winningLotto == null) throw new IllegalStateException("당첨 로또가 설정되지 않았습니다.");

        GameResult gameResult = new GameResult();
        lottos.setAllLottoResult(gameResult, winningLotto);
        return gameResult;
    }

    public void validatePurchaseMoneyRange(int money) {
        if(money < LottoPolicy.LOTTO_PRICE) throw new IllegalArgumentException("1000원 이상의 금액을 입력해야 합니다.");
    }

    public void validatePurchaseMoneyRange(int totalCount, int manualCount) {
        if(totalCount < manualCount) throw new IllegalArgumentException("구입한 로또 수 만큼만 수동으로 구매할 수 있습니다.");
    }
}
