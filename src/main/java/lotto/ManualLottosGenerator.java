package lotto;

import java.util.List;

public class ManualLottosGenerator implements LottosGenerator {
    private final LottoForm lottoForm;

    public ManualLottosGenerator(LottoForm lottoForm) {
        this.lottoForm = lottoForm;
    }

    @Override
    public List<Lotto> generate() {
        return lottoForm.manualLottos();
    }
}
