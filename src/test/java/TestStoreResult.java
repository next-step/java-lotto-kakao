import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import model.LottoNumber;
import model.StoreResult;

public class TestStoreResult {

	@Test
	void 외부에서_당첨번호를_입력했을_때_저장한다(){

		String input = "1, 2, 3, 4, 5, 6";
		List<Integer> answer = List.of(1, 2, 3, 4, 5, 6);
		StoreResult store = new StoreResult();

		assertThat(store.setNumber(input)).isEqualTo(answer);
	}

	@Test
	void 보너스_볼을_입력했을_때_저장한다(){
		String input = "7";
		Integer answer = 7;
		StoreResult store = new StoreResult();
		assertThat(store.setBonus(input)).isEqualTo(answer);
	}

	@Test
	void 당첨번호_입력은_정해진_개수와_일치해야_한다(){
		try{
			StoreResult storeResult = new StoreResult();
			storeResult.setNumber("12, 13, 15, 17, 18, 29, 27");
			fail();
		}
		catch(RuntimeException e){
			assertThat(e.getMessage()).isEqualTo("당첨 번호는 "+LottoNumber.LOTTO_SIZE+"개의 숫자로 이루어져야 합니다");
		}
	}

	@Test
	void 당첨번호는_중복될_수_없다(){
		try{
			StoreResult storeResult = new StoreResult();
			storeResult.setNumber("12, 13, 15, 17, 18, 18");
			fail();
		}
		catch(RuntimeException e){
			assertThat(e.getMessage()).isEqualTo("당첨 번호는 중복될 수 없습니다");
		}
	}

	@Test
	void 당첨번호는_정해진_범위의_수여야_한다(){
		try{
			StoreResult storeResult = new StoreResult();
			storeResult.setNumber("12, 13, 15, 17, 18, 50");
			fail();
		}
		catch(RuntimeException e){
			assertThat(e.getMessage()).isEqualTo("당첨 번호는 "+LottoNumber.MIN_NUMBER +"~"+LottoNumber.MAX_NUMBER+" 사이의 수로 이루어져야 합니다.");
		}
	}

	@Test
	void 보너스_번호는_정해진_범위의_수여야_한다() {
		try {
			StoreResult storeResult = new StoreResult();
			storeResult.setBonus("0");
			fail();
		} catch (RuntimeException e) {
			assertThat(e.getMessage()).isEqualTo("보너스 번호는 "+LottoNumber.MIN_NUMBER +"~"+LottoNumber.MAX_NUMBER+" 사이의 수로 이루어져야 합니다.");
		}
	}

	@Test
	void 보너스_번호는_기존_당첨번호와_중복된_숫자로_설정될_수_없다() {
		try {
			StoreResult storeResult = new StoreResult();
			storeResult.setNumber("1, 2, 3, 4, 5, 6");
			storeResult.setBonus("3");
			fail();
		} catch (RuntimeException e) {
			assertThat(e.getMessage()).isEqualTo("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
		}
	}

	@Test
	void 보너스_번호는_문자열로_표현된_숫자_하나이어야_한다() {
		try {
			StoreResult storeResult = new StoreResult();
			storeResult.setBonus("ㅁ");
			fail();
		} catch (RuntimeException e) {
			assertThat(e.getMessage()).isEqualTo("보너스 번호는 "+LottoNumber.MIN_NUMBER +"~"+LottoNumber.MAX_NUMBER+" 사이의 수로 이루어져야 합니다.");
		}
	}
}
