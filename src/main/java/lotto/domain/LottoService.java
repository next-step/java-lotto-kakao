package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoService {

	public LottoBundle purchaseAuto(int count) {
		List<Lotto> purchasedLottos = IntStream.range(0, count)
				.mapToObj(it -> LottoGenerator.generateLotto())
				.toList();
		return new LottoBundle(purchasedLottos);
	}
}
