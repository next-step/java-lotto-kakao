package lotto.domain;

import static lotto.domain.LottoPolicy.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoMachine {
	public LottoPurchase issue(int amount, int manualCount, List<Lotto> manualLottos) {
		int autoCount = amount / LOTTO_PRICE - manualCount;
		List<Lotto> lottos = new ArrayList<>(manualLottos);
		lottos.addAll(generate(autoCount));
		return LottoPurchase.of(lottos, manualCount, autoCount, amount);
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
		List<Integer> values = lottoNumberPool.subList(0, REQUIRED_LOTTO_SIZE)
			.stream()
			.map(LottoNumber::getValue)
			.toList();
		return Lotto.from(values);
	}
}
