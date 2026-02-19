package lotto;

import java.util.List;

public class LottoController {
	private final InputView inputView;
	private final OutputView outputView;

	public LottoController(InputView inputView, OutputView outputView) {
		this.inputView = inputView;
		this.outputView = outputView;
	}

	public void run() {
		LottoPurchase lottoPurchase = readLottoPurchase(readPurchaseMoney());
		LottoTickets lottoTickets = getLottoTickets(lottoPurchase);
		outputView.printPurchaseResult(lottoTickets, lottoPurchase);
		LottoTicket winningTicket = readWinningTicket();
		LottoAnswer lottoAnswer = readLottoAnswer(winningTicket);
		printLottoResult(lottoTickets, lottoAnswer, lottoPurchase);
	}

	private LottoTickets getLottoTickets(LottoPurchase lottoPurchase) {
		LottoTickets lottoTickets = readManualLottoTickets(lottoPurchase.manualLottoCount());
		LottoTickets autoLottoTickets = generateLottoTickets(lottoPurchase.autoLottoCount());
		lottoTickets.merge(autoLottoTickets);
		return lottoTickets;
	}

	private Money readPurchaseMoney() {
		try {
			return inputView.readMoney();
		} catch (IllegalArgumentException exception) {
			outputView.printError(exception.getMessage());
			return readPurchaseMoney();
		}
	}

	private LottoPurchase readLottoPurchase(Money purchaseMoney) {
		try {
			return new LottoPurchase(purchaseMoney, readManualLottoCount());
		} catch (IllegalArgumentException exception) {
			outputView.printError(exception.getMessage());
			return readLottoPurchase(purchaseMoney);
		}
	}

	private Count readManualLottoCount() {
		return new Count(inputView.readManualLottoCount());
	}

	private LottoTickets generateLottoTickets(Count count) {
		if (count.isZero()) {
			return new LottoTickets(List.of());
		}
		return LottoTicketGenerator.generate(count.value());
	}

	private LottoTicket readWinningTicket() {
		try {
			return inputView.readWinningNumbers();
		} catch (IllegalArgumentException exception) {
			outputView.printError(exception.getMessage());
			return readWinningTicket();
		}
	}

	private LottoAnswer readLottoAnswer(LottoTicket winningTicket) {
		try {
			return new LottoAnswer(winningTicket, inputView.readBonusNumber());
		} catch (IllegalArgumentException exception) {
			outputView.printError(exception.getMessage());
			return readLottoAnswer(winningTicket);
		}
	}

	private LottoTickets readManualLottoTickets(Count count){
		if (count.isZero()) {
			return new LottoTickets(List.of());
		}
		return inputView.readManualLottoTickets(count.value());
	}

	private void printLottoResult(LottoTickets lottoTickets, LottoAnswer lottoAnswer, LottoPurchase lottoPurchase) {
		LottoStatistics lottoStatistics = lottoTickets.buildStatistics(lottoAnswer);
		outputView.printStatistics(lottoStatistics, lottoPurchase.purchaseMoney());
	}
}
