package lotto.domain;

import java.util.List;
import java.util.Optional;

import lombok.Getter;
import lotto.exception.LottoValidationException;

@Getter
public class WinningNumbers {
	private final Lotto lotto;
	private final LottoNumber bonusNumber;

	private WinningNumbers(Lotto lotto, LottoNumber bonusNumber) {
		validate(lotto, bonusNumber);
		this.lotto = lotto;
		this.bonusNumber = bonusNumber;
	}

	public static WinningNumbers of(Lotto numbers, LottoNumber bonusNumber) {
		return new WinningNumbers(numbers, bonusNumber);
	}

	public Optional<LottoResult> match(Lotto lotto) {
		int matchCount = countMatches(lotto.getNumbers(), this.lotto.getNumbers());
		boolean bonusMatched = lotto.getNumbers().contains(bonusNumber);
		return LottoResult.of(matchCount, bonusMatched);
	}

	private static void validate(Lotto numbers, LottoNumber bonusNumber) {
		validateNotNull(numbers);
		validateNotNull(bonusNumber);
		validateBonusDistinct(numbers, bonusNumber);
	}

	private static void validateNotNull(Lotto numbers) {
		if (numbers == null) {
			throw new LottoValidationException("당첨 번호는 null일 수 없습니다.");
		}
	}

	private static void validateNotNull(LottoNumber bonusNumber) {
		if (bonusNumber == null) {
			throw new LottoValidationException("보너스 번호는 null일 수 없습니다.");
		}
	}

	private static void validateBonusDistinct(Lotto numbers, LottoNumber bonusNumber) {
		if (numbers.getNumbers().contains(bonusNumber)) {
			throw new LottoValidationException("보너스 번호는 당첨 번호와 같을 수 없습니다.");
		}
	}

	private static int countMatches(List<LottoNumber> lottoNumbers, List<LottoNumber> winningNumbers) {
		long count = lottoNumbers.stream()
			.filter(winningNumbers::contains)
			.count();
		return Math.toIntExact(count);
	}
}
