package lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CompositeLottosGenerator implements LottosGenerator {
    private final List<LottosGenerator> children;

    public CompositeLottosGenerator(List<LottosGenerator> children) {
        validate(children);
        this.children = List.copyOf(children);
    }

    @Override
    public LottoBundle generate() {
        List<Lotto> merged = new ArrayList<>();
        for (LottosGenerator child : children) {
            merged.addAll(child.generate().asList());
        }
        return new LottoBundle(merged);
    }

    private void validate(List<LottosGenerator> children) {
        final String message = "조합 생성기에 null이 포함될 수 없습니다.";
        try {
            Objects.requireNonNull(children, message);
            for (LottosGenerator child : children) {
                Objects.requireNonNull(child, message);
            }
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(message);
        }
    }
}
