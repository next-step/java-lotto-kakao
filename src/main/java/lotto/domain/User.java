package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User {

    public static final String LOTTO_COUNT_MISMATCH_EXCEPTION = "로또 개수가 구매 금액과 일치하지 않습니다.";
    public static final String MANUAL_LOTTO_COUNT_EXCEEDING_EXCEPTION = "구매할 수 있는 로또의 개수보다 많은 개수가 입력되었습니다.";
    public static final String MANUAL_LOTTO_COUNT_NEGATIVE_EXCEPTION = "수동 로또 구매 개수에 음수가 입력되었습니다.";

    private final Price price;
    private final int manualLottoCount;
    private final List<Lotto> lottos;

    public User(Price price, List<Lotto> lottos, int manualLottoCount) {
        validateManualLottoCount(price.getLottoCount(), manualLottoCount);
        validateLottoCount(price.getLottoCount(), lottos.size());
        this.price = price;
        this.manualLottoCount = manualLottoCount;
        this.lottos = Collections.unmodifiableList(new ArrayList<>(lottos));
    }

    private void validateManualLottoCount(int total, int manual) {
        if (manual < 0) {
            throw new IllegalArgumentException(MANUAL_LOTTO_COUNT_NEGATIVE_EXCEPTION);
        }
        if (manual > total) {
            throw new IllegalArgumentException(MANUAL_LOTTO_COUNT_EXCEEDING_EXCEPTION);
        }
    }

    private void validateLottoCount(int expected, int actual) {
        if (expected != actual) {
            throw new IllegalArgumentException(LOTTO_COUNT_MISMATCH_EXCEPTION);
        }
    }

    public int getPrice() {
        return price.getPrice();
    }

    public int getLottoCount() {
        return price.getLottoCount();
    }

    public int getManualLottoCount() {
        return manualLottoCount;
    }

    public int getAutoLottoCount() {
        return price.getLottoCount() - manualLottoCount;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
