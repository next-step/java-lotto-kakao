package lotto.generator;

import lotto.domain.Lotto;

import java.util.ArrayList;
import java.util.List;

// leaf 클래스
public class AutoLottoGenerator implements LottoGenerator {

    private final NumberGenerator generator;

    public AutoLottoGenerator(NumberGenerator generator) {
        this.generator = generator;
    }

    @Override
    public List<Lotto> generate(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto(generator.generate()));
        }
        return lottos;
    }
}
