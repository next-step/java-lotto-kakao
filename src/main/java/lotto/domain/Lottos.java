package lotto.domain;

import java.util.List;

public class Lottos {

    private final List<Lotto> values;

    private Lottos(List<Lotto> values) {
        this.values = List.copyOf(values);
    }

    public static Lottos from(List<Lotto> values) {
        return new Lottos(List.copyOf(values));
    }

    public Lottos merge(Lottos other) {
        List<Lotto> current = List.copyOf(this.values);
        List<Lotto> otherList = other.asList();

        current.addAll(otherList);
        return Lottos.from(current);
    }

    public int size() {
        return values.size();
    }

    public List<Lotto> asList() {
        return values;
    }
}
