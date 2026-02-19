package lotto.generator;

import lotto.domain.Lotto;

import java.util.List;

public class GeneratorTask {

    private LottoGenerator generator;
    private int count;

    public GeneratorTask(LottoGenerator generator, int count) {
        this.generator = generator;
        this.count = count;
    }

    public List<Lotto> generate() {
        return generator.generate(count);
    }
}
