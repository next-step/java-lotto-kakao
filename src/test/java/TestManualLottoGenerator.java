import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import java.util.List;

import org.junit.jupiter.api.Test;
import model.Lotto;
import model.LottoNumber;
import model.ManualLottoGenerator;

public class TestManualLottoGenerator {
	@Test
	void 반환된_로또는_정해진_갯수와_일치한_수를_가져야_한다() {
		ManualLottoGenerator generator = new ManualLottoGenerator(List.of(1, 2, 3, 4, 5, 6));
		Lotto lotto = generator.issueLotto();
		assertThat(lotto.size()).isEqualTo(LottoNumber.LOTTO_SIZE);
	}

	@Test
	void 수동으로_생성된_로또번호는_정해진_범위의_수이다() {
		ManualLottoGenerator generatorA = new ManualLottoGenerator(List.of(1, 2, 3, 0, 5, 6));

		assertThatThrownBy(() -> generatorA.issueLotto() )
			.isInstanceOf(RuntimeException.class)
			.hasMessage("로또 번호는 "+LottoNumber.MIN_NUMBER+"~"+LottoNumber.MAX_NUMBER+"까지의 숫자여야 한다.");
		ManualLottoGenerator generatorB = new ManualLottoGenerator(List.of(1, 2, 3, 46, 5, 6));
		assertThatThrownBy(() -> generatorB.issueLotto() )
			.isInstanceOf(RuntimeException.class)
			.hasMessage("로또 번호는 "+LottoNumber.MIN_NUMBER+"~"+LottoNumber.MAX_NUMBER+"까지의 숫자여야 한다.");
	}

	@Test
	void 수동으로_생성된_로또번호는_중복되는_수가_없어야_한다() {
		ManualLottoGenerator generator = new ManualLottoGenerator(List.of(1, 2, 3, 3, 5, 6));

		assertThatThrownBy(() -> generator.issueLotto() )
			.isInstanceOf(RuntimeException.class)
			.hasMessage("로또 번호는 중복될 수 없습니다");
	}

	@Test
	void 수동으로_생성된_로또번호는_정해진_갯수와_일치해야한다() {
		ManualLottoGenerator generatorA = new ManualLottoGenerator(List.of(1, 2, 5, 6, 7));
		assertThatThrownBy(() -> generatorA.issueLotto() )
			.isInstanceOf(RuntimeException.class)
			.hasMessage("로또 번호 개수가 "+LottoNumber.LOTTO_SIZE+"개가 아닙니다.");
		ManualLottoGenerator generatorB = new ManualLottoGenerator(List.of(1, 2, 3, 5, 6, 7, 8));
		assertThatThrownBy(() -> generatorB.issueLotto() )
			.isInstanceOf(RuntimeException.class)
			.hasMessage("로또 번호 개수가 "+LottoNumber.LOTTO_SIZE+"개가 아닙니다.");
	}
}
