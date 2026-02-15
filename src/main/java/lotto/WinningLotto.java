package lotto;

public class WinningLotto {
    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto lotto, LottoNumber bonusNumber) {
        validateBonusNumber(lotto, bonusNumber);
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }
    public boolean matchBonus(Lotto matchLotto){
        return matchLotto.contains(bonusNumber);
    }

    private void validateBonusNumber(Lotto lotto, LottoNumber bonusNumber) {
        if (lotto.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호가 잘못 되었습니다.");
        }
    }

    public Rank judge(Lotto compare){
        int count = lotto.matchCount(compare);
        boolean matchBonus = matchBonus(compare);

        return Rank.valueOf(count, matchBonus);
    }
}