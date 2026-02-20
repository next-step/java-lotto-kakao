package lotto.domain;

import java.util.List;

public interface LottoGeneratorFactory {
	LottoGenerator createManual(List<List<Integer>> manualNumbers);

	LottoGenerator createRandom(int randomCount);
}
