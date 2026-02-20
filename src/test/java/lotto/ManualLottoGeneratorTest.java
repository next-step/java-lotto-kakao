package lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

public class ManualLottoGeneratorTest {

	@Test
	void 수동_로또_생성_성공() {
		List<Ball> balls1 = List.of(
			new Ball(1), new Ball(2), new Ball(3),
			new Ball(4), new Ball(5), new Ball(6)
		);
		List<Ball> balls2 = List.of(
			new Ball(7), new Ball(8), new Ball(9),
			new Ball(10), new Ball(11), new Ball(12)
		);
		List<List<Ball>> manualLottos = List.of(balls1, balls2);

		ManualLottoGenerator generator = new ManualLottoGenerator(manualLottos);
		LottoList result = generator.generate();

		assertThat(result.getLottos()).hasSize(2);
	}

	@Test
	void 수동_로또_단일_생성() {
		List<Ball> balls = List.of(
			new Ball(10), new Ball(20), new Ball(30),
			new Ball(40), new Ball(41), new Ball(42)
		);
		List<List<Ball>> manualLottos = List.of(balls);

		ManualLottoGenerator generator = new ManualLottoGenerator(manualLottos);
		LottoList result = generator.generate();

		assertThat(result.getLottos()).hasSize(1);
	}

	@Test
	void 빈_수동_로또_생성() {
		List<List<Ball>> manualLottos = List.of();

		ManualLottoGenerator generator = new ManualLottoGenerator(manualLottos);
		LottoList result = generator.generate();

		assertThat(result.getLottos()).isEmpty();
	}

	@Test
	void 수동_로또_중복_번호_검증() {
		List<Ball> duplicateBalls = List.of(
			new Ball(1), new Ball(2), new Ball(3),
			new Ball(4), new Ball(5), new Ball(5) // 중복
		);
		List<List<Ball>> manualLottos = List.of(duplicateBalls);

		ManualLottoGenerator generator = new ManualLottoGenerator(manualLottos);

		assertThatThrownBy(() -> generator.generate())
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("로또 번호는 중복 없이 6개여야 합니다.");
	}
}

