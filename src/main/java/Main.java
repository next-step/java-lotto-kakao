import java.util.Scanner;
import Interface.LottoGenerator;
import model.AutoLottoGenerator;
import model.Buyers;
import model.Lotto;
import model.ManualLottoGenerator;
import model.StoreResult;

public class Main {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Buyers buyer = new Buyers();
		try {
			System.out.println("구입금액을 입력해 주세요.");
			if(!sc.hasNextLine()) return;
			Integer money = Integer.parseInt(sc.nextLine().trim());

			System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
			Integer manualAmount = Integer.parseInt(sc.nextLine().trim());

			System.out.println("수동으로 구매할 번호를 입력해 주세요. \n ex) 8, 21, 23, 41, 42, 43");
			for( Integer i = 1; i <= manualAmount; i++ ) {
				LottoGenerator manualGenerator = new ManualLottoGenerator(sc.nextLine().trim());
				buyer.buyLotto(1000, manualGenerator);
				money -= 1000;
			}

			System.out.println("수동으로 "+manualAmount+"장, 자동으로 "+money/1000+"개를 구매했습니다.");
			LottoGenerator autoGenerator = new AutoLottoGenerator();
			buyer.buyLotto(money, autoGenerator);
			for (Lotto lotto : buyer.getLottos().getLottoList()) {
				System.out.println(lotto.getNumbers());
			}

			System.out.println("지난 주 당첨 번호를 입력해 주세요.");
			if(!sc.hasNextLine()) return;
			StoreResult store = new StoreResult();
			store.setNumber(sc.nextLine());

			System.out.println("보너스 볼을 입력해 주세요.");
			if(!sc.hasNextLine()) return;
			store.setBonus(sc.nextLine());

			buyer.setResult(store.getResult());
			buyer.compare();
		}
		catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}
}
