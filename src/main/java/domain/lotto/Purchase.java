package domain.lotto;

import java.util.List;

public class Purchase {

    private final List<List<Integer>> manualLottosNumbers;
    private final int autoLottoCount;

    public Purchase(int purchasePrice, List<List<Integer>> manualLottosNumbers) {
        validate(purchasePrice, manualLottosNumbers);
        this.manualLottosNumbers = manualLottosNumbers;
        this.autoLottoCount = purchasePrice / Lotto.PRICE - manualLottosNumbers.size();
    }

    private void validate(int purchasePrice, List<List<Integer>> manualLottosNumbers) {
        validatePrice(purchasePrice);
        validateLottoCount(purchasePrice, manualLottosNumbers);
    }

    private void validatePrice(int price) {
        if (price <= 0) {
            throw new IllegalArgumentException("로또 발행 금액은 양수여야 합니다.");
        }
        if (price % Lotto.PRICE != 0) {
            throw new IllegalArgumentException("로또 발행 금액은 1000원 단위여야 합니다.");
        }
    }

    private void validateLottoCount(int purchasePrice, List<List<Integer>> manualLottosNumbers) {
        int totalCount = purchasePrice / Lotto.PRICE;
        int manualCount = manualLottosNumbers.size();
        int autoCount = totalCount - manualCount;
        if (manualCount > totalCount) {
            throw new IllegalArgumentException("구매 금액보다 수동 로또 개수가 많습니다.");
        }
        if (autoCount < 0) {
            throw new IllegalArgumentException("자동 로또 개수는 0보다 작을 수 없습니다.");
        }
    }

    public List<List<Integer>> getManualLottosNumbers() {
        return manualLottosNumbers;
    }

    public int getAutoLottoCount() {
        return autoLottoCount;
    }
}
