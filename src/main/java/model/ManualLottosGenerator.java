package model;

import java.util.List;

public class ManualLottosGenerator implements LottosGenerator {
    private final List<Lotto> manualLottos;

    public ManualLottosGenerator(List<Lotto> manualLottos) {
        this.manualLottos = manualLottos;
    }

    @Override
    public Lottos generate() {
        Lottos lottos = new Lottos();

        for (Lotto manualLotto : manualLottos) {
            lottos.add(manualLotto);
        }

        return lottos;
    }
}
