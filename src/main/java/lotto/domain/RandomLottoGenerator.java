package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lotto.exception.LottoValidationException;

public class RandomLottoGenerator implements LottoGenerator {
	private final int randomCount;

	public RandomLottoGenerator(int randomCount) {
		validateCount(randomCount);
		this.randomCount = randomCount;
	}

	@Override
	public List<Lotto> generate() {
		List<Lotto> lottos = new ArrayList<>();
		for (int i = 0; i < randomCount; i++) {
			lottos.add(generateLotto());
		}
		return List.copyOf(lottos);
	}

	private Lotto generateLotto() {
		List<LottoNumber> lottoNumberPool = LottoNumber.getPool();
		Collections.shuffle(lottoNumberPool);
		List<Integer> values = lottoNumberPool.subList(0, Lotto.requiredSize())
			.stream()
			.map(LottoNumber::getValue)
			.toList();
		return Lotto.from(values);
	}

	private void validateCount(int count) {
		if (count < 0) {
			throw new LottoValidationException("구매 수는 0 이상이어야 합니다.");
		}
	}
}
