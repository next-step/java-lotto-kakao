package lotto.domain;

import lotto.exception.LottoErrorCode;
import lotto.exception.LottoException;

public class PurchaseCount {
    private final int totalCount;
    private final int manualCount;

    public PurchaseCount(int totalCount, int manualCount) {
        validate(totalCount, manualCount);
        this.totalCount = totalCount;
        this.manualCount = manualCount;
    }

    private void validate(int totalCount, int manualCount) {
        if (totalCount < 0) {
            throw new LottoException(LottoErrorCode.NEGATIVE_TOTAL_COUNT);
        }
        if (manualCount < 0) {
            throw new LottoException(LottoErrorCode.NEGATIVE_MANUAL_COUNT);
        }
        if (manualCount > totalCount) {
            throw new LottoException(LottoErrorCode.EXCEED_MANUAL_COUNT);
        }
    }

    public int getManualCount() {
        return manualCount;
    }

    public int getAutoCount() {
        return totalCount - manualCount;
    }
}
