package lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class LottosTest {

	@Test
	void manualLottoAddTest() {
		List<Ball> myBall1 = new ArrayList<>(List.of(new Ball(1), new Ball(2), new Ball(3)
			, new Ball(4), new Ball(5), new Ball(6)));
		Lotto myLotto1 = new Lotto(myBall1);

		List<Ball> myBall2 = new ArrayList<>(List.of(new Ball(7), new Ball(8), new Ball(9)
			, new Ball(10), new Ball(11), new Ball(12)));
		Lotto myLotto2 = new Lotto(myBall2);
		Lottos maualLottos = new Lottos();
		maualLottos.add(myLotto1);
		maualLottos.add(myLotto2);
		assertThat(maualLottos.getLottoCount()).isEqualTo(2);
	}

	@Test
	void autoLottoAddTest() {
		final int lottoCount = 6;
		Lottos autoLottos = new Lottos();
		autoLottos.addRandomLotto(lottoCount);
		assertThat(autoLottos.getLottoCount()).isEqualTo(lottoCount);
	}

	@Test
	void mixedLottoAddTest() {
		Lottos myLottos = new Lottos();
		final int manualCount = 6;
		for (int i = 0; i < manualCount; i++) {
			List<Ball> myBall = new ArrayList<>(List.of(new Ball(1), new Ball(2), new Ball(3)
				, new Ball(4), new Ball(5), new Ball(6)));
			Lotto myLotto = new Lotto(myBall);
			myLottos.add(myLotto);
		}
		final int autoLottoCount = 6;
		myLottos.addRandomLotto(autoLottoCount);
		assertThat(myLottos.getLottoCount()).isEqualTo(manualCount + autoLottoCount);

	}

}
