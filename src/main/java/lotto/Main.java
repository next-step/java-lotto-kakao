package lotto;

public class Main {
    public static void main(String[] args) {

        OutputView outputView = new OutputView();
        InputView inputView = new InputView();

        LottoGame game = new LottoGame();

        outputView.printPurchaseAmountInput();
        int purchaseAmount = inputView.inputPurchaseAmount();
        int totalLottoCount = game.calculateTotalLottoCount(purchaseAmount);

        outputView.printManualLottoPurchaseAmountInput();
        int manualLottoPurchaseAmount = inputView.inputManualLottoPurchaseAmount();
        int manualLottoCount = game.calculateManualLottoCount(totalLottoCount, manualLottoPurchaseAmount);

        outputView.printManualLottoNumberInput();
        for (int i = 0; i < manualLottoCount; i++) {
            game.purchaseManualLotto(inputView.inputLottoNumber());
        }

        outputView.printPurchaseAmount(manualLottoCount, totalLottoCount);

        game.purchaseLotto(totalLottoCount - manualLottoCount, new RandomLottoNumberStrategy());

        outputView.printLottoNumbers(game.getLottoListAsList());

        outputView.printWinningLottoInput();
        String input = inputView.inputLottoNumber();

        outputView.printBonusNumberInput();
        String bonusInput = inputView.inputLottoNumber();

        game.createWinningLotto(input, bonusInput);

        GameResult gameResult = game.generateGameResult();

        outputView.printResult(gameResult);
        outputView.printRateOfReturn(gameResult);
    }
}
