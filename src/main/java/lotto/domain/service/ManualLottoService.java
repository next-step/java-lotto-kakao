package lotto.domain.service;

import lotto.domain.Lotto;
import lotto.domain.LottoPlayer;
import lotto.domain.Lottos;
import lotto.domain.pick.LottoPickStrategy;
import lotto.view.input.InputView;
import lotto.view.output.OutputView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManualLottoService implements LottoService {


    public static final String NEGATIVE_MANUAL_COUNT_MSG = "수동 구매 수는 0 이상이어야 합니다.";
    public static final String TOO_MANY_MANUAL_COUNT_MSG = "수동 구매 수가 전체 구매 수보다 클 수 없습니다.";

    private final LottoPickStrategy autoPickStrategy;
    private final LottoPickStrategy manualPickStrategy;
    private final InputView inputView;
    private final OutputView outputView;

    public ManualLottoService(
            final LottoPickStrategy autoPickStrategy,
            final LottoPickStrategy manualPickStrategy,
            final InputView inputView,
            final OutputView outputView
    ) {
        this.autoPickStrategy = autoPickStrategy;
        this.manualPickStrategy = manualPickStrategy;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    @Override
    public LottoPlayer createPlayer() {
        int price = readPrice();
        int totalCount = countFrom(price);

        int manualCount = readManualCount(totalCount);
        int autoCount = totalCount-manualCount;

        outputView.printManualLottoRequest();

        Lottos manualLottos = buyLottos(manualCount, manualPickStrategy);
        Lottos autoLottos = buyLottos(autoCount, autoPickStrategy);
        outputView.printManualBuyResult(manualCount, totalCount);

        Lottos all = Lottos.merge(manualLottos, autoLottos);
        outputView.printLottos(all);

        return LottoPlayer.of(price, all);
    }

    private int readPrice() {
        outputView.printPriceRequest();
        int price = inputView.inputNumber();
        validatePrice(price);
        return price;
    }

    private void validatePrice(int price) {
        if (price < LottoService.ONE_LOTTO_PRICE) {
            throw new IllegalArgumentException(LottoService.PRICE_NOT_ENOUGH_MSG);
        }
    }

    private int countFrom(int price) {
        return price / LottoService.ONE_LOTTO_PRICE;
    }

    private int readManualCount(int totalCount) {
        outputView.printManualCountRequest();   
        int manualCount = inputView.inputNumber();
        validateManualCount(manualCount, totalCount);

        return manualCount;
    }

    private void validateManualCount(int manualCount, int totalCount) {
        if (manualCount < 0) {
            throw new IllegalArgumentException(NEGATIVE_MANUAL_COUNT_MSG);
        }

        if (manualCount > totalCount) {
            throw new IllegalArgumentException(TOO_MANY_MANUAL_COUNT_MSG);
        }
    }

    private Lottos buyLottos(int count, LottoPickStrategy strategy) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(createSortedLotto(strategy));
        }
        return Lottos.from(lottos);
    }

    private Lotto createSortedLotto(LottoPickStrategy strategy) {
        List<Integer> numbers = new ArrayList<>(strategy.generate());
        Collections.sort(numbers);
        return Lotto.fromIntegers(numbers);
    }



}
