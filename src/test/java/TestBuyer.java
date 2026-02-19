import model.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestBuyer {

    @Test
    void 당첨번호와_가지고_있는_로또번호_간의_올바른_비교가_이루어져야_한다() {

        Lottos lottos = new Lottos();
        Lotto lotto = new Lotto(1, 2, 3, 4, 5, 6);

        lottos.add(lotto);

        StoreResult result = new StoreResult();

        result.setNumber("1, 2, 3, 4, 5, 10");
        result.setBonus("7");

        Buyers buyers = new Buyers();

        buyers.setLottos(lottos);
        buyers.addSpentMoney(12000);
        buyers.setResult(result.getResult());

        assertThat(buyers.compare().get(LottoRank.THIRD)).isEqualTo(1);
    }

    @Test
    void 등수별_당첨금_합으로_수익률을_계산한다() {

        Lottos lottos = new Lottos();
        Lotto lotto = new Lotto(1, 2, 3, 4, 5, 6);
        lottos.add(lotto);

        StoreResult result = new StoreResult();
        result.setNumber("1, 2, 3, 4, 5, 10");
        result.setBonus("7");

        Buyers buyers = new Buyers();
        buyers.setLottos(lottos);
        buyers.addSpentMoney(12000);
        buyers.setResult(result.getResult());
        buyers.compare();

        assertThat(buyers.getProfitRate()).isEqualTo(125.0);
    }
}
