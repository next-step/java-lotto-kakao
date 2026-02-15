package lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoController {

    private final LottoService lottoService;

    public LottoController() {
        this.lottoService = new LottoService();
    }

    public void run() {
        try {
            Money money = new Money(InputView.readPurchaseAmount());
            PurchasedLottoBundle purchasedLottoBundle = buyLottos(money);
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
        LottoBundle autoLottoBundle = lottoService.purchase(count-purchasedCount.manualCount()); // 앞서 논의한 Service 활용
        OutputView.printPurchaseCount(purchasedCount.manualCount(), purchasedCount.autoCount());
        OutputView.printLottoBundle(autoLottoBundle);
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
        Lotto lotto = makeLotto(winningNumbers);
        int bonusNumber = InputView.readingBonusNumber();

        return new WinningLotto(lotto, new LottoNumber(bonusNumber));
    }

    private Lotto makeLotto(String LottoNumbers){
        return new Lotto(Arrays.stream(LottoNumbers.split(", "))
                .map((String number) -> new LottoNumber(Integer.parseInt(number)))
                .collect(Collectors.toList()));
    }

    private LottoBundle makeManualLottoBundle(int count){
        List<String> inputList = InputView.readManualNumbers(count);
        List<Lotto> lottoList = new ArrayList<>();
        for(int times=0; times<count; times++){
            lottoList.add(makeLotto(inputList.get(times)));
        }
        return new LottoBundle(lottoList);
    }
}