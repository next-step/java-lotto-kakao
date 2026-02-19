package domains;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoStore {
    private static final List<LottoNumber> ALL_NUMBERS = LottoNumber.values();

    public LottoTickets buy(Money money, ManualLottos manualLottos) {
        money.validatePurchasable(manualLottos.size());

        int totalCount = money.availableLottoCount();
        int autoCount = totalCount - manualLottos.size();

        //  남은 횟수만큼 자동 로또를 생성합니다.
        List<Lotto> autoLottos = generateAutoLottos(autoCount);

        // 수동 로또와 자동 로또를 합칩니다.
        List<Lotto> allLottos = new ArrayList<>(manualLottos.getLottos());
        allLottos.addAll(autoLottos);

        return new LottoTickets(allLottos);
    }

    private List<Lotto> generateAutoLottos(int count) {
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            lottos.add(generateRandomLotto());
        }

        return lottos;
    }

    private Lotto generateRandomLotto() {
        List<LottoNumber> numbers = new ArrayList<>(ALL_NUMBERS);

        Collections.shuffle(numbers);

        List<LottoNumber> result = new ArrayList<>(numbers.subList(0, 6));
        return new Lotto(result);
    }
}
