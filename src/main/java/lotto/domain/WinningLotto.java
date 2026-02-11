package lotto.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class WinningLotto {

    public static final String BONUS_DUPLICATE_FAIL_MSG = "당첨번호와 중복된 숫자를 보너스 번호로 등록할 수 없습니다.";

    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto lotto, int bonusNumber) {
        validate(lotto, bonusNumber);
        this.lotto = lotto;
        this.bonusNumber = new LottoNumber(bonusNumber);
    }

    private void validate(Lotto lotto, int bonusNumber) {
        validateDuplicate(lotto, bonusNumber);
    }

    private void validateDuplicate(Lotto lotto, int bonusNumber) {
        if (lotto.getNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException(BONUS_DUPLICATE_FAIL_MSG);
        }
    }

    public LottoStatus judge(Lotto playerLotto) {
        int matchCount = lotto.matchCount(playerLotto);
        boolean hasBonus = playerLotto.contains(bonusNumber);
        return LottoStatus.judgeGameStatus(matchCount, hasBonus);
    }

    public Map<LottoStatus, Integer> countByStatus(List<Lotto> playerLottos) {
        Map<LottoStatus, Integer> counts = new EnumMap<>(LottoStatus.class);
        for (Lotto playerLotto: playerLottos) {
            LottoStatus status = judge(playerLotto);
            counts.put(status, counts.getOrDefault(status, 0) + 1);
        }
        return counts;
    }

    public Lotto getLotto() {
        return lotto;
    }

    public int getBonusNumber() {
        return bonusNumber.getValue();
    }
}
