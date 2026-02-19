package lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

public class CompositeLottoGeneratorTest {

	@Test
	void 복합_로또_생성_수동_자동_혼합() {
		List<Ball> manualBalls1 = List.of(
			new Ball(1), new Ball(2), new Ball(3),
			new Ball(4), new Ball(5), new Ball(6)
		);
		List<Ball> manualBalls2 = List.of(
			new Ball(10), new Ball(20), new Ball(30),
			new Ball(40), new Ball(41), new Ball(42)
		);
		ManualLottoGenerator manualGenerator = new ManualLottoGenerator(
			List.of(manualBalls1, manualBalls2)
		);

		AutoLottoGenerator autoGenerator = new AutoLottoGenerator(3);

		CompositeLottoGenerator composite = new CompositeLottoGenerator(
			List.of(manualGenerator, autoGenerator)
		);
		LottoList result = composite.generate();

		assertThat(result.getLottos()).hasSize(5);
	}

	@Test
	void 복합_로또_생성_자동_만() {
		AutoLottoGenerator autoGenerator1 = new AutoLottoGenerator(2);
		AutoLottoGenerator autoGenerator2 = new AutoLottoGenerator(3);

		CompositeLottoGenerator composite = new CompositeLottoGenerator(
			List.of(autoGenerator1, autoGenerator2)
		);
		LottoList result = composite.generate();

		assertThat(result.getLottos()).hasSize(5);
	}

	@Test
	void 복합_로또_생성_수동_만() {
		List<Ball> manualBalls1 = List.of(
			new Ball(1), new Ball(2), new Ball(3),
			new Ball(4), new Ball(5), new Ball(6)
		);
		List<Ball> manualBalls2 = List.of(
			new Ball(7), new Ball(8), new Ball(9),
			new Ball(10), new Ball(11), new Ball(12)
		);
		ManualLottoGenerator manualGenerator = new ManualLottoGenerator(
			List.of(manualBalls1, manualBalls2)
		);

		CompositeLottoGenerator composite = new CompositeLottoGenerator(
			List.of(manualGenerator)
		);
		LottoList result = composite.generate();

		assertThat(result.getLottos()).hasSize(2);
	}

	@Test
	void 복합_로또_생성_빈_목록() {
		CompositeLottoGenerator composite = new CompositeLottoGenerator(List.of());
		LottoList result = composite.generate();

		assertThat(result.getLottos()).isEmpty();
	}

	@Test
	void 복합_로또_생성_빈_제너레이터() {
		ManualLottoGenerator manualGenerator = new ManualLottoGenerator(List.of());
		AutoLottoGenerator autoGenerator = new AutoLottoGenerator(0);

		CompositeLottoGenerator composite = new CompositeLottoGenerator(
			List.of(manualGenerator, autoGenerator)
		);
		LottoList result = composite.generate();

		assertThat(result.getLottos()).isEmpty();
	}

	@Test
	void 복합_로또_생성_각_로또_볼_개수_확인() {
		List<Ball> manualBalls = List.of(
			new Ball(5), new Ball(10), new Ball(15),
			new Ball(20), new Ball(25), new Ball(30)
		);
		ManualLottoGenerator manualGenerator = new ManualLottoGenerator(List.of(manualBalls));
		AutoLottoGenerator autoGenerator = new AutoLottoGenerator(2);

		CompositeLottoGenerator composite = new CompositeLottoGenerator(
			List.of(manualGenerator, autoGenerator)
		);
		LottoList result = composite.generate();

		for (Lotto lotto : result.getLottos()) {
			assertThat(lotto.getBalls()).hasSize(6);
		}
	}

	@Test
	void 복합_로또_생성_여러_제너레이터() {
		List<Ball> manualBalls1 = List.of(
			new Ball(1), new Ball(2), new Ball(3),
			new Ball(4), new Ball(5), new Ball(6)
		);
		List<Ball> manualBalls2 = List.of(
			new Ball(7), new Ball(8), new Ball(9),
			new Ball(10), new Ball(11), new Ball(12)
		);
		ManualLottoGenerator manual1 = new ManualLottoGenerator(List.of(manualBalls1));
		ManualLottoGenerator manual2 = new ManualLottoGenerator(List.of(manualBalls2));
		AutoLottoGenerator auto1 = new AutoLottoGenerator(2);
		AutoLottoGenerator auto2 = new AutoLottoGenerator(1);

		CompositeLottoGenerator composite = new CompositeLottoGenerator(
			List.of(manual1, manual2, auto1, auto2)
		);
		LottoList result = composite.generate();

		assertThat(result.getLottos()).hasSize(5);
	}
}

