package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LottoMachine {
	private static final int LOTTO_PRICE = 1_000;
	private static final int REQUIRED_SIZE = 6;

	public List<Lotto> issueRandom(int count) {
		validateCount(count);
		return generate(count);
	}

	public List<Lotto> issueManual(List<List<Integer>> manualNumbers) {
		validateManualNumbers(manualNumbers);
		return manualNumbers.stream()
			.map(Lotto::from)
			.toList();
	}

	public int calculateRandomCountFromAmount(int amount, int manualCount) {
		validateAmount(amount);
		validateCount(manualCount);
		int totalCount = amount / LOTTO_PRICE;
		if (manualCount > totalCount) {
			throw new IllegalArgumentException("Manual ticket count must not exceed total tickets.");
		}
		return totalCount - manualCount;
	}

	private List<Lotto> generate(int count) {
		List<Lotto> lottos = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			lottos.add(generateLotto());
		}
		return lottos;
	}

	private Lotto generateLotto() {
		List<LottoNumber> lottoNumberPool = LottoNumber.getPool();
		Collections.shuffle(lottoNumberPool);
		List<Integer> values = lottoNumberPool.subList(0, REQUIRED_SIZE)
			.stream()
			.map(LottoNumber::getValue)
			.toList();
		return Lotto.from(values);
	}

	private void validateAmount(int amount) {
		if (amount < LOTTO_PRICE) {
			throw new IllegalArgumentException(String.format("Amount must be at least %s", LOTTO_PRICE));
		}
	}

	private void validateCount(int count) {
		if (count < 0) {
			throw new IllegalArgumentException("Ticket count must not be negative.");
		}
	}

	private void validateManualNumbers(List<List<Integer>> manualNumbers) {
		if (manualNumbers == null) {
			throw new IllegalArgumentException("Manual numbers must not be null.");
		}
		boolean hasNull = manualNumbers.stream().anyMatch(Objects::isNull);
		if (hasNull) {
			throw new IllegalArgumentException("Manual numbers must not contain null.");
		}
	}
}
