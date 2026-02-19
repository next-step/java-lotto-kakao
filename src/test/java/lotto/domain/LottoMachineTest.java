package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.exception.LottoValidationException;

class LottoMachineTest {
	@DisplayName("로또 머신은 등록된 생성기의 결과를 순서대로 병합해야 한다")
	@Test
	void issue_withGenerators_mergesResultsInOrder() {
		LottoGenerator manualGenerator = new ManualLottoGenerator(List.of(List.of(1, 2, 3, 4, 5, 6)));
		LottoGenerator randomGenerator = () -> List.of(Lotto.from(List.of(7, 8, 9, 10, 11, 12)));
		LottoMachine machine = new LottoMachine(List.of(manualGenerator, randomGenerator));

		List<Lotto> issued = machine.issue();

		assertThat(issued).hasSize(2);
		assertThat(issued.get(0).getNumbers())
			.extracting(LottoNumber::getValue)
			.containsExactly(1, 2, 3, 4, 5, 6);
		assertThat(issued.get(1).getNumbers())
			.extracting(LottoNumber::getValue)
			.containsExactly(7, 8, 9, 10, 11, 12);
	}

	@DisplayName("로또 머신 생성 시 생성기 목록이 null이면 LottoValidationException이 발생해야 한다")
	@Test
	void constructor_withNullGenerators_throwsLottoValidationException() {
		assertThatThrownBy(() -> new LottoMachine(null))
			.isInstanceOf(LottoValidationException.class);
	}

	@DisplayName("로또 머신 생성 시 생성기 목록에 null이 포함되면 LottoValidationException이 발생해야 한다")
	@Test
	void constructor_withNullGeneratorElement_throwsLottoValidationException() {
		List<LottoGenerator> generators = new ArrayList<>();
		generators.add(() -> List.of(Lotto.from(List.of(1, 2, 3, 4, 5, 6))));
		generators.add(null);

		assertThatThrownBy(() -> new LottoMachine(generators))
			.isInstanceOf(LottoValidationException.class);
	}

	@DisplayName("생성 결과가 null이면 LottoValidationException이 발생해야 한다")
	@Test
	void issue_whenGeneratorReturnsNull_throwsLottoValidationException() {
		LottoMachine machine = new LottoMachine(List.of(() -> null));

		assertThatThrownBy(machine::issue)
			.isInstanceOf(LottoValidationException.class);
	}

	@DisplayName("생성 결과에 null 로또가 포함되면 LottoValidationException이 발생해야 한다")
	@Test
	void issue_whenGeneratedLottosContainNull_throwsLottoValidationException() {
		List<Lotto> generated = new ArrayList<>();
		generated.add(Lotto.from(List.of(1, 2, 3, 4, 5, 6)));
		generated.add(null);
		LottoMachine machine = new LottoMachine(List.of(() -> generated));

		assertThatThrownBy(machine::issue)
			.isInstanceOf(LottoValidationException.class);
	}
}
