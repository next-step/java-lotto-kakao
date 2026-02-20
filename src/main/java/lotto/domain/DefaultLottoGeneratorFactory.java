package lotto.domain;

import java.util.List;

public class DefaultLottoGeneratorFactory implements LottoGeneratorFactory {
	@Override
	public LottoGenerator createManual(List<List<Integer>> manualNumbers) {
		return new ManualLottoGenerator(manualNumbers);
	}

	@Override
	public LottoGenerator createRandom(int randomCount) {
		return new RandomLottoGenerator(randomCount);
	}
}
