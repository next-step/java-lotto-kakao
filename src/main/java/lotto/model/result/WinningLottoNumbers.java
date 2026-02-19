package lotto.model.result;

import lotto.model.ticket.LottoNumber;
import lotto.model.ticket.LottoTicket;

import java.util.List;

public class WinningLottoNumbers {

	private final List<LottoNumber> normalLottoNumbers;
	private final LottoNumber bonusLottoNumber;

	public WinningLottoNumbers(List<LottoNumber> normalLottoNumbers, LottoNumber bonusLottoNumber) {
		validateBonusInNormal(normalLottoNumbers, bonusLottoNumber);
		if(normalLottoNumbers.size() != LottoTicket.LOTTO_LENGTH){
			throw new IllegalArgumentException("당첨 번호는 " + LottoTicket.LOTTO_LENGTH +"자리이어야 합니다");
		}
		this.normalLottoNumbers = normalLottoNumbers;
		this.bonusLottoNumber = bonusLottoNumber;
	}

	public Rank match(LottoTicket myLottoTicket) {
		int normalCount = Math.toIntExact(normalLottoNumbers.stream().filter(myLottoTicket::isMatch).count());
		boolean hasBonus = myLottoTicket.isMatch(bonusLottoNumber);

		return Rank.from(normalCount, hasBonus);
	}

	private void validateBonusInNormal(List<LottoNumber> normalLottoNumbers, LottoNumber bonusLottoNumber) {
		boolean isBonusInNormal = normalLottoNumbers.stream()
				.anyMatch(bonusLottoNumber::equals);

		if (isBonusInNormal) {
			throw new IllegalArgumentException("보너스 번호는 일반 번호에 포함되지 않아야 합니다.");
		}
	}
}
