package lotto.controller;

import java.util.List;

import lotto.model.common.Money;
import lotto.model.machine.LottoMachine;
import lotto.model.machine.LottoMachineGeneratedResult;
import lotto.model.machine.LottoPurchaseSession;
import lotto.model.result.LottoResult;
import lotto.model.result.Rank;
import lotto.model.result.WinningLottoNumbers;
import lotto.model.ticket.LottoNumber;
import lotto.model.ticket.TicketManualGeneratorCommand;
import lotto.model.ticket.TicketRandomGeneratorCommand;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

	private final InputView inputView;
	private final OutputView outputView;
	private final LottoMachine lottoMachine;

	public LottoController(LottoMachine lottoMachine) {
		inputView = new InputView();
		outputView = new OutputView();
		this.lottoMachine = lottoMachine;
	}

	public void run() {
		try {
			executeLotto();
		} catch (RuntimeException runtimeException) {
			outputView.printError(runtimeException.getMessage());
		}
	}

	private void executeLotto() {
		Money purchasePrice = inputView.readPurchasePrice();
		LottoPurchaseSession lottoPurchaseSession = getLottoPurchaseSession(purchasePrice);

		LottoMachineGeneratedResult machineGeneratedResult = lottoPurchaseSession.getResult();
		outputView.printPurchasedTicketCount(machineGeneratedResult.lottoTickets().size());
		outputView.printLottoTickets(machineGeneratedResult.lottoTickets());

		WinningLottoNumbers winningLottoNumbers = readWinningLottoNumbers();
		LottoResult lottoResult = createLottoResult(machineGeneratedResult, winningLottoNumbers);
		outputView.printLottoResult(lottoResult);
	}

	private LottoPurchaseSession getLottoPurchaseSession(Money purchasePrice) {
		LottoPurchaseSession lottoPurchaseSession = new LottoPurchaseSession(lottoMachine, purchasePrice);
		int manualCount = inputView.readManualPurchaseTicketCount();

		List<List<Integer>> numbers = inputView.readManualLottoNumbers(manualCount);
		TicketManualGeneratorCommand manualCommand = new TicketManualGeneratorCommand(numbers);
		lottoPurchaseSession.purchase(manualCommand);

		TicketRandomGeneratorCommand randomCommand = new TicketRandomGeneratorCommand(lottoPurchaseSession.getPurchasableTicketCount());
		lottoPurchaseSession.purchase(randomCommand);
		return lottoPurchaseSession;
	}

	private WinningLottoNumbers readWinningLottoNumbers() {
		List<LottoNumber> winningNormalNumbers = readWinningNormalNumbers();
		LottoNumber bonusNumber = inputView.readBonusNumber();
		return new WinningLottoNumbers(winningNormalNumbers, bonusNumber);
	}

	private List<LottoNumber> readWinningNormalNumbers() {
		List<Integer> winningNormalIntegerNumbers = inputView.readWinningNormalNumbers();
		return winningNormalIntegerNumbers.stream().map(LottoNumber::of).toList();
	}

	private LottoResult createLottoResult(LottoMachineGeneratedResult machineGeneratedResult, WinningLottoNumbers winningLottoNumbers) {
		Money totalPrice = machineGeneratedResult.totalPrice();
		List<Rank> ranks = machineGeneratedResult.lottoTickets().stream()
				.map(winningLottoNumbers::match).toList();
		return new LottoResult(totalPrice, ranks);
	}
}
