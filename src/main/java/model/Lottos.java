package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {

    public static final int COST = 1000;

    private final List<Lotto> lottos;
    private final int manualCount;
    private final int autoCount;

    public Lottos(int price) {
        this(price, 0, null);
    }

    public Lottos(int price, int manualCount, List<LottoNumbers> lottoNumbersList) {
        validate(price);
        this.manualCount = manualCount;
        this.autoCount = price / COST - manualCount;
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < manualCount; i++) {
            lottos.add(new Lotto(lottoNumbersList.get(i)));
        }
        for (int i = 0; i < autoCount; i++) {
            lottos.add(issue());
        }
        this.lottos = lottos;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public int getManualCount() {
        return manualCount;
    }

    public int getAutoCount() {
        return autoCount;
    }

    private Lotto issue() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 45; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            lottoNumbers.add(LottoNumber.of(numbers.get(i)));
        }
        return new Lotto(new LottoNumbers(lottoNumbers));
    }

    private void validate(int price) {
        if (price < 0) {
            throw new IllegalArgumentException("음수로는 구매할 수 없습니다.");
        }
        if (price == 0) {
            throw new IllegalArgumentException("0원으로는 구매할 수 없습니다.");
        }
        if (price % COST != 0) {
            throw new IllegalArgumentException(COST + "원 단위로 입력해주세요.");
        }
    }
}
