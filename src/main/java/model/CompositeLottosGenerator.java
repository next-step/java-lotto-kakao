package model;

import java.util.Arrays;
import java.util.List;

public class CompositeLottosGenerator implements LottosGenerator {
    private final List<LottosGenerator> lottosGenerators;

    public CompositeLottosGenerator(LottosGenerator... lottosGenerators) {
        this.lottosGenerators = Arrays.asList(lottosGenerators);
    }

    @Override
    public Lottos generate() {
        Lottos lottos = new Lottos();

        for (LottosGenerator lottosGenerator : lottosGenerators) {
            for (Lotto lotto : lottosGenerator.generate().getLottoList()) {
                lottos.add(lotto);
            }
        }

        return lottos;
    }
}
