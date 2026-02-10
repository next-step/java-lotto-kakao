package lotto.domain;

public class WinningLotto {

    private final Lotto lotto;
    private final int bonusNumber;

    public WinningLotto(Lotto lotto, int bonusNumber) {
        validate(lotto, bonusNumber);
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    private void validate(Lotto lotto, int bonusNumber) {
        validateRange(bonusNumber);
        validateDuplicate(lotto, bonusNumber);
    }

    private void validateRange(int bonusNumber) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException("1 ~ 45 범위를 벗어나는 숫자가 입력되었습니다.");
        }
    }

    private void validateDuplicate(Lotto lotto, int bonusNumber) {
        if (lotto.getNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException("당첨번호와 중복된 숫자를 보너스 번호로 등록할 수 없습니다.");
        }
    }

    public Lotto getLotto() {
        return lotto;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
