package lotto;

import java.util.Arrays;
import java.util.stream.Collectors;

public class LottoController {

    private final LottoService lottoService;

    public LottoController() {
        this.lottoService = new LottoService();
    }

    public void run() {
        try {
            Money money = new Money(InputView.readPurchaseAmount());
            LottoBundle lottos = buyLottos(money);
            WinningLotto winningLotto = makeWinningLotto();
            processResult(lottos, winningLotto, money);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            run(); // 예외 발생 시 재시도 로직
        }
    }

    private LottoBundle buyLottos(Money money) {
        int count = money.calculateLottoCount();
        OutputView.printPurchaseCount(count);
        LottoBundle bundle = lottoService.purchase(count); // 앞서 논의한 Service 활용
        OutputView.printLottoBundle(bundle);
        return bundle;
    }

    private void processResult(LottoBundle lottos, WinningLotto winningLotto, Money money) {
        LottoResult lottoResult = lottos.getLottoResult(winningLotto);

        OutputView.printStatisticsHeader();
        OutputView.printResult(lottoResult);
        OutputView.printYield(lottoResult.calculateYield(money));

    }

    private WinningLotto makeWinningLotto() {
        String winningNumbers = InputView.readWinningNumbers();
        Lotto lotto = new Lotto(Arrays.stream(winningNumbers.split(", "))
                .map((String number) -> new LottoNumber(Integer.parseInt(number)))
                .collect(Collectors.toList()));
        int bonusNumber = InputView.readingBonusNumber();

        return new WinningLotto(lotto, new LottoNumber(bonusNumber));
    }
}