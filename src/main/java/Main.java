import model.Buyers;
import model.Lottos;
import model.StoreResult;
import view.InputView;
import view.OutputView;

import java.util.List;

public class Main {
	public static void main(String[] args) {
		InputView inputView = new InputView();
		OutputView outputView = new OutputView();
		Buyers buyer = new Buyers();
		try {
			Integer money = inputView.readPurchaseMoney();
			if(money == null) return;

			Integer manualLottoCount = inputView.readManualLottoCount();
			if(manualLottoCount == null) return;

			List<String> manualLottoInputs = inputView.readManualLottoNumbers(manualLottoCount);

			Lottos lottos = buyer.buyLotto(money, manualLottoCount, manualLottoInputs);
			outputView.printPurchaseSummary(manualLottoCount, lottos.getQuantity() - manualLottoCount);
			outputView.printLottoTickets(lottos);

			String winningNumbers = inputView.readWinningNumbers();
			if(winningNumbers == null) return;
			StoreResult store = new StoreResult();
			store.setNumber(winningNumbers);

			String bonusNumber = inputView.readBonusNumber();
			if(bonusNumber == null) return;
			store.setBonus(bonusNumber);

			buyer.setResult(store.getResult());
			outputView.printWinningStatistics(buyer.compare(), buyer.getProfitRate());
		}
		catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}
}
