package lotto;

import java.util.List;
import java.util.Objects;

public class ManualLottosGenerator implements LottosGenerator {
    private final List<Lotto> manualLottos;

    public ManualLottosGenerator(List<Lotto> manualLottos) {
        validate(manualLottos);
        this.manualLottos = List.copyOf(manualLottos);
    }

    @Override
    public LottoBundle generate() {
        return new LottoBundle(manualLottos);
    }

    private void validate(List<Lotto> manualLottos) {
        final String message = "수동 로또 목록에 null이 포함될 수 없습니다.";
        try {
            Objects.requireNonNull(manualLottos, message);
            for (Lotto manualLotto : manualLottos) {
                Objects.requireNonNull(manualLotto, message);
            }
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(message);
        }
    }
}
