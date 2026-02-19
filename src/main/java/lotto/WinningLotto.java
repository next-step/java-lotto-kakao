package lotto;

import java.util.*;

import static lotto.LottoNumberParser.parseBonusNumber;
import static lotto.LottoNumberParser.parseLottoNumbers;

public class WinningLotto {
    // manual
    private WinningLottoNumbers winningLottoNumbers;
    private LottoNumber bonusNumber;

    public WinningLotto(String input, String bonusInput) {
        this.winningLottoNumbers = new WinningLottoNumbers(parseLottoNumbers(input)); // 6개 입력
        validateDistinctBonusNumber(winningLottoNumbers.getLottoNumberList(), parseBonusNumber(bonusInput));
        this.bonusNumber = LottoNumber.from(parseBonusNumber(bonusInput)); // 보너스 점수 입력
    }

    public WinningLottoNumbers getWinningLottoNumbers() {
        return this.winningLottoNumbers;
    }

    public LottoNumber getBonusNumber() {
        return this.bonusNumber;
    }

    public int getBonusNumberValue() {
        return this.bonusNumber.getNumber();
    }

    public boolean contains(LottoNumber lottoNumber) {
        return winningLottoNumbers.getLottoNumberList().contains(lottoNumber);
    }

    public boolean isContainBonusNumber(LottoNumbers lottoNumbers) {
        return lottoNumbers.contains(bonusNumber);
    }

    public void validateDistinctBonusNumber(List<LottoNumber> lottoNumberList, Integer number) {
        if(lottoNumberList.contains(LottoNumber.from(number))) throw new IllegalArgumentException("로또에 보너스와 중복된 숫자가 존재합니다.");
    }
}
