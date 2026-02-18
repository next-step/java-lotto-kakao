package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {

    public static final int COST = 1000;

    private final List<Lotto> lottos;

    public Lottos(int price) {
        validate(price);
        int ticketCount = price / COST;
        List<Lotto> lottos = new ArrayList<>();
        while (lottos.size() < ticketCount) {
            Lotto lotto = issue();
            lottos.add(lotto);
        }
        this.lottos = lottos;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    private Lotto issue() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 45; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            lottoNumbers.add(new LottoNumber(numbers.get(i)));
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
