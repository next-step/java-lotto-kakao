package lotto.domain;

public class WinningLotto {

    private final Lottos lottos;
    private final int bonusNumber;

    public WinningLotto(Lottos lottos, int bonusNumber) {
        validate(lottos, bonusNumber);
        this.lottos = lottos;
        this.bonusNumber = bonusNumber;
    }

    private void validate(Lottos lottos, int bonusNumber) {
        validateRange(bonusNumber);
        validateDuplicate(lottos, bonusNumber);
    }

    private void validateRange(int bonusNumber) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException("1 ~ 45 범위를 벗어나는 숫자가 입력되었습니다.");
        }
    }

    private void validateDuplicate(Lottos lottos, int bonusNumber) {
        if (lottos.getNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException("당첨번호와 중복된 숫자를 보너스 번호로 등록할 수 없습니다.");
        }
    }

    public LottoStatus judge(Lottos playerLottos) {
        int matchCount = lottos.matchCount(playerLottos);
        boolean hasBonus = playerLottos.getNumbers().contains(bonusNumber);
        return LottoStatus.judgeGameStatus(matchCount, hasBonus);
    }

    public Lottos getLotto() {
        return lottos;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
