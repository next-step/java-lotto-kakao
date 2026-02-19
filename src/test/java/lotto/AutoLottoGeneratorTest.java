package lotto;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AutoLottoGeneratorTest {

	@Test
	void 자동_로또_다건_생성() {
		int count = 5;
		AutoLottoGenerator generator = new AutoLottoGenerator(count);
		LottoList result = generator.generate();

		assertThat(result.getLottos()).hasSize(count);
	}

	@Test
	void 자동_로또_단일_생성() {
		int count = 1;
		AutoLottoGenerator generator = new AutoLottoGenerator(count);
		LottoList result = generator.generate();

		assertThat(result.getLottos()).hasSize(1);
	}

	@Test
	void 생성된_로또_볼_개수_확인() {
		int count = 3;
		AutoLottoGenerator generator = new AutoLottoGenerator(count);
		LottoList result = generator.generate();

		for (Lotto lotto : result.getLottos()) {
			assertThat(lotto.getBalls()).hasSize(6);
		}
	}

	@Test
	void 생성된_로또_각각_중복_없음() {
		AutoLottoGenerator generator = new AutoLottoGenerator(10);
		LottoList result = generator.generate();

		for (Lotto lotto : result.getLottos()) {
			assertThat(lotto.getBalls()).hasSize(6);
			assertThat(lotto.getBalls().stream().distinct().count()).isEqualTo(6);
		}
	}

	@Test
	void 생성된_로또_번호_범위_확인() {
		AutoLottoGenerator generator = new AutoLottoGenerator(5);
		LottoList result = generator.generate();

		for (Lotto lotto : result.getLottos()) {
			for (Ball ball : lotto.getBalls()) {
				assertThat(ball.getValue()).isGreaterThanOrEqualTo(1).isLessThanOrEqualTo(45);
			}
		}
	}

	@Test
	void 자동_로또_개수_영점() {
		int count = 0;
		AutoLottoGenerator generator = new AutoLottoGenerator(count);
		LottoList result = generator.generate();

		assertThat(result.getLottos()).isEmpty();
	}
}

