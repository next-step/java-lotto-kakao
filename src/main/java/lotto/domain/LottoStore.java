package lotto.domain;

import java.util.ArrayList;
import java.util.List;

import static lotto.util.LottoAutoCreator.lottoAutoCreate;

public class LottoStore {

    public MyLotto buy(List<Lotto> manualTickets, int autoCount) {

        List<Lotto> purchased = new ArrayList<>(manualTickets);

        for (int i = 0; i < autoCount; i++) {
            purchased.add(new Lotto(lottoAutoCreate()));
        }

        return new MyLotto(purchased);
    }
}
