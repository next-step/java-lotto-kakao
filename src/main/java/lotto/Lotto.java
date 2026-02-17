package lotto;

import java.util.*;
import java.util.stream.Collectors;

public class Lotto {
    private static final int LENGTH = 6;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private final Set<LottoNumber> lottoNumberSet;

    Lotto(List<Integer> numbers) {
        this(convertListToSet(numbers));
    }

    private Lotto(Set<LottoNumber> lottoNumberSet) {
        validate(lottoNumberSet);
        this.lottoNumberSet = Set.copyOf(lottoNumberSet);
    }

    private static Set<LottoNumber> convertListToSet(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toSet());
    }

    public static Lotto random() {
        List<Integer> pool = new ArrayList<>();
        for (int i = MIN_NUMBER; i <= MAX_NUMBER; i++) {
            pool.add(i);
        }
        Collections.shuffle(pool);
        return new Lotto(pool.subList(0, LENGTH));
    }

    private void validate(Set<LottoNumber> lottoNumberSet) {
        if (lottoNumberSet.size() != LENGTH) {
            throw new IllegalArgumentException(
                    String.format("로또 숫자는 중복없이 %d개여야 합니다", LENGTH)
            );
        }
    }

    public int matchCount(Lotto other) {
        Set<LottoNumber> intersection = new HashSet<>(lottoNumberSet);
        intersection.retainAll(other.lottoNumberSet);

        return intersection.size();
    }

    public boolean hasBonus(LottoNumber bonus) {
        return lottoNumberSet.contains(bonus);
    }

    public List<LottoNumber> numbers() {
        List<LottoNumber> list = new ArrayList<>(lottoNumberSet);
        Collections.sort(list);
        return list;
    }

    @Override
    public String toString() {
        return this.numbers().toString();
    }

    public boolean contains(LottoNumber number) {
        return lottoNumberSet.contains(number);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return Objects.equals(lottoNumberSet, lotto.lottoNumberSet);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(lottoNumberSet);
    }
}
