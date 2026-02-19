package lotto;

import java.util.List;
import java.util.stream.Stream;

public class AutoLottosGenerator implements LottosGenerator {
    private final long count;

    public AutoLottosGenerator(long count) {
        if (count < 0) {
            throw new IllegalArgumentException("자동 로또 개수는 0 이상이어야 합니다.");
        }
        this.count = count;
    }

    @Override
    public List<Lotto> generate() {
        return Stream.generate(Lotto::random)
                .limit(count)
                .toList();
    }
}
