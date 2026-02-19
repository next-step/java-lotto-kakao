package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lotto.exception.LottoValidationException;

public class LottoMachine {
	private final List<LottoGenerator> generators;

	public LottoMachine(List<LottoGenerator> generators) {
		validateGenerators(generators);
		this.generators = List.copyOf(generators);
	}

	public List<Lotto> issue() {
		List<Lotto> lottos = new ArrayList<>();
		for (LottoGenerator generator : generators) {
			List<Lotto> generated = generator.generate();
			validateGeneratedLottos(generated);
			lottos.addAll(generated);
		}
		return List.copyOf(lottos);
	}

	private void validateGenerators(List<LottoGenerator> generators) {
		if (generators == null) {
			throw new LottoValidationException("생성기 목록은 null일 수 없습니다.");
		}
		boolean hasNull = generators.stream().anyMatch(Objects::isNull);
		if (hasNull) {
			throw new LottoValidationException("생성기 목록에 null이 포함될 수 없습니다.");
		}
	}

	private void validateGeneratedLottos(List<Lotto> lottos) {
		if (lottos == null) {
			throw new LottoValidationException("생성 결과는 null일 수 없습니다.");
		}
		boolean hasNull = lottos.stream().anyMatch(Objects::isNull);
		if (hasNull) {
			throw new LottoValidationException("생성 결과에 null 로또가 포함될 수 없습니다.");
		}
	}
}
