package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoMachine {
	private static final int LOTTO_PRICE = 1_000;
	private static final int REQUIRED_SIZE = 6;

	public List<Lotto> issue(int amount) {
		validateAmount(amount);
		int count = amount / LOTTO_PRICE;
		return generate(count);
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
}
