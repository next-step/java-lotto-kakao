package domains;

import java.util.ArrayList;
import java.util.List;

public class LottoStore {
    public LottoTickets buy(LottoGenerator... generators) {
        List<Lotto> allLottos = new ArrayList<>();

        for (LottoGenerator generator : generators) {
            allLottos.addAll(generator.generate());
        }

        return new LottoTickets(allLottos);
    }
}