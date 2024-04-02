package domain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class Lotto {
    private final List<LottoNumber> numbers;

    public static Lotto of(List<Integer> values) {
        return new Lotto(values);
    }

    private Lotto(List<Integer> values) {
        Objects.requireNonNull(values);
        this.numbers = values.stream().map(LottoNumber::of).sorted().collect(Collectors.toUnmodifiableList());
    }

    public List<LottoNumber> numbers() {
        return numbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return Objects.equals(numbers, lotto.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }
}
