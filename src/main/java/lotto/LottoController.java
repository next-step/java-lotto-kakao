package lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoController {
    private final LottoService lottoService;
    private final LottoParser lottoParser;

    public LottoController() {
        this.lottoService = new LottoService();
        this.lottoParser = new LottoParser();
    }

    public void run() {
        try {
            Money money = new Money(InputView.readPurchaseAmount());
            PurchasedLottoBundle purchasedLottoBundle = buyLottos(money);
            OutputView.printLottoBundle(purchasedLottoBundle);
            WinningLotto winningLotto = makeWinningLotto();
            processResult(purchasedLottoBundle, winningLotto, money);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            run(); // 예외 발생 시 재시도 로직
        }
    }

    private PurchasedLottoBundle buyLottos(Money money) {
        int count = money.calculateLottoCount();
        int manualCount = InputView.readManualCount();
        PurchasedCount purchasedCount = new PurchasedCount(manualCount, count);
        LottoBundle manualLottoBundle = makeManualLottoBundle(purchasedCount.manualCount());
        LottoBundle autoLottoBundle = lottoService.purchaseAutoLottoBundle(purchasedCount.autoCount());
        OutputView.printPurchaseCount(purchasedCount.manualCount(), purchasedCount.autoCount());
        return new PurchasedLottoBundle(manualLottoBundle, autoLottoBundle);
    }

    private void processResult(PurchasedLottoBundle lottos, WinningLotto winningLotto, Money money) {
        LottoResult lottoResult = lottos.makeLottoResult(winningLotto);

        OutputView.printStatisticsHeader();
        OutputView.printResult(lottoResult);
        OutputView.printYield(lottoResult.calculateYield(money));
    }

    private WinningLotto makeWinningLotto() {
        String winningNumbers = InputView.readWinningNumbers();
        Lotto lotto = lottoParser.parse(winningNumbers);
        int bonusNumber = InputView.readingBonusNumber();

        return new WinningLotto(lotto, new LottoNumber(bonusNumber));
    }

    private LottoBundle makeManualLottoBundle(int count){
        List<String> inputList = InputView.readManualNumbers(count);
        List<Lotto> lottoList = new ArrayList<>();
        for(int times=0; times<count; times++){
            lottoList.add(lottoParser.parse(inputList.get(times)));
        }
        return lottoService.purchaseManualLottoBundle(lottoList);
    }
}
