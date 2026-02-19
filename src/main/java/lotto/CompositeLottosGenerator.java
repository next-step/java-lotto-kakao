package lotto;

import java.util.List;

public class CompositeLottosGenerator implements LottosGenerator {
    private final List<LottosGenerator> generators;

    public CompositeLottosGenerator(LottosGenerator... generators) {
        this.generators = List.of(generators);
    }

    @Override
    public List<Lotto> generate() {
        return generators.stream()
                .map(LottosGenerator::generate)
                .flatMap(List::stream)
                .toList();
    }
}
