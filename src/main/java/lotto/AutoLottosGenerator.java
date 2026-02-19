package lotto;

import java.util.ArrayList;
import java.util.List;

public class AutoLottosGenerator implements LottosGenerator {
    private final long autoCount;
    private final LottoGenerator lottoGenerator;

    public AutoLottosGenerator(long autoCount, LottoGenerator lottoGenerator) {
        validate(autoCount, lottoGenerator);
        this.autoCount = autoCount;
        this.lottoGenerator = lottoGenerator;
    }

    @Override
    public LottoBundle generate() {
        List<Lotto> lottos = new ArrayList<>();
        for (long c = 0; c < autoCount; c++) {
            lottos.add(lottoGenerator.generate());
        }
        return new LottoBundle(lottos);
    }

    private void validate(long autoCount, LottoGenerator lottoGenerator) {
        if (autoCount < 0L) {
            throw new IllegalArgumentException("자동 구매 수량은 음수일 수 없습니다.");
        }
        if (lottoGenerator == null) {
            throw new IllegalArgumentException("로또 생성기는 null일 수 없습니다.");
        }
    }
}
