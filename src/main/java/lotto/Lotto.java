package lotto;

import java.util.*;

public class Lotto {
    private static final int LENGTH = 6;
    private final Set<LottoNumber> lottoNumberSet;

    public Lotto(List<Integer> numbers) {
        this(convert(numbers));
    }

    public Lotto(Integer... numbers) {
        this(Arrays.asList(numbers));
    }

    public Lotto(Set<LottoNumber> lottoNumberSet) {
        validate(lottoNumberSet);
        this.lottoNumberSet = lottoNumberSet;
    }

    private static Set<LottoNumber> convert(List<Integer> numbers) {
        Set<LottoNumber> result = new HashSet<>();
        for (Integer number : numbers) {
            result.add(new LottoNumber(number));
        }
        return result;
    }

    public static Lotto random() {
        List<Integer> pool = new ArrayList<>();
        for (int i = 1; i <= 45; i++) {
            pool.add(i);
        }
        Collections.shuffle(pool);
        return new Lotto(pool.subList(0, LENGTH));
    }

    private void validate(Set<LottoNumber> lottoNumberSet) {
        if (lottoNumberSet.size() != LENGTH) {
            throw new IllegalArgumentException(
                    String.format("로또 숫자는 %d개여야 합니다", LENGTH)
            );
        }
    }

    public int matchCount(Lotto win) {
        Set<LottoNumber> intersection = new HashSet<>(lottoNumberSet);
        intersection.retainAll(win.lottoNumberSet);

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
}
