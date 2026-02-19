package lotto;

import java.util.List;

public class CompositeLottoGenerator implements LottoGenerator {

	private final List<LottoGenerator> lottoGenerators;

	public CompositeLottoGenerator(List<LottoGenerator> lottoGenerators) {
		this.lottoGenerators = lottoGenerators;
	}

	@Override
	public LottoList generate() {
		List<Lotto> lottos = lottoGenerators.stream()
			.map(LottoGenerator::generate)
			.flatMap(l -> l.getLottos().stream())
			.toList();
		return new LottoList(lottos);
	}
}
