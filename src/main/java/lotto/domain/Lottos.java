package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private final List<Lotto> values;

    private Lottos(List<Lotto> values) {
        this.values = List.copyOf(values);
    }

    public static Lottos from(List<Lotto> values) {
        return new Lottos(List.copyOf(values));
    }

    public static Lottos merge(Lottos l1, Lottos l2) {
        List<Lotto> list1 = l1.asList();
        List<Lotto> list2 = l2.asList();
        List<Lotto> all = new ArrayList<>();

        all.addAll(list1);
        all.addAll(list2);

        return Lottos.from(all);
    }

    public int size() {
        return values.size();
    }

    public List<Lotto> asList() {
        return values;
    }
}
