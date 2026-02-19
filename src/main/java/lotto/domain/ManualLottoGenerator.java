package lotto.domain;

import java.util.List;
import java.util.Objects;

public class ManualLottoGenerator implements LottoGenerator {
	private final List<Lotto> lottos;

	public ManualLottoGenerator(List<List<Integer>> manualNumbers) {
		validateManualNumbers(manualNumbers);
		this.lottos = manualNumbers.stream()
			.map(Lotto::from)
			.toList();
	}

	@Override
	public List<Lotto> generate() {
		return lottos;
	}

	private void validateManualNumbers(List<List<Integer>> manualNumbers) {
		if (manualNumbers == null) {
			throw new IllegalArgumentException("수동 번호는 null일 수 없습니다.");
		}
		boolean hasNull = manualNumbers.stream().anyMatch(Objects::isNull);
		if (hasNull) {
			throw new IllegalArgumentException("수동 번호에 null이 포함될 수 없습니다.");
		}
	}
}
