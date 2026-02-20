package lotto;

import java.util.List;

public class ManualLottoGenerator implements LottoGenerator {
	private final List<List<Ball>> manualLottos;

	public ManualLottoGenerator(List<List<Ball>> manualLottos) {
		this.manualLottos = manualLottos;
	}

	@Override
	public LottoList generate() {
		List<Lotto> lottos = manualLottos.stream()
			.map(Lotto::new)
			.toList();
		return new LottoList(lottos);
	}
}
