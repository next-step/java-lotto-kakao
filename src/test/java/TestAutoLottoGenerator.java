import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import model.Lotto;
import org.junit.jupiter.api.Test;
import model.AutoLottoGenerator;
import model.LottoNumber;

public class TestAutoLottoGenerator {
	@Test
	void 자동으로_생성된_로또번호는_정해진_갯수와_일치한_수를_가져야_한다() {
		AutoLottoGenerator generator = new AutoLottoGenerator();

		try{
			Lotto lotto = generator.issueLotto();
		}catch(RuntimeException e){
			assertThat(e.getMessage()).isEqualTo("로또 번호 개수가 "+ LottoNumber.LOTTO_SIZE +"개가 아닙니다.");
		}
	}

	@Test
	void 자동으로_생성된_로또번호는_정해진_범위의_수이다() {
		AutoLottoGenerator generator = new AutoLottoGenerator();

		try{
			Lotto lotto = generator.issueLotto();
		}catch(RuntimeException e){
			assertThat(e.getMessage()).isEqualTo("로또 번호는 "+LottoNumber.MIN_NUMBER+"~"+LottoNumber.MAX_NUMBER+"까지의 숫자여야 한다.");
		}
	}

	@Test
	void 자동으로_생성된_로또번호는_중복되는_수가_없어야_한다() {
		AutoLottoGenerator generator = new AutoLottoGenerator();

		try{
			Lotto lotto = generator.issueLotto();
		}catch(RuntimeException e){
			assertThat(e.getMessage()).isEqualTo("로또 번호는 중복될 수 없습니다");
		}
	}
}
