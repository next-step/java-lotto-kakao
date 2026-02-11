package lotto;

import money.Money;

import java.util.*;

public class Lotto {
    public static final long PRICE = 1000L;
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

    static int numberCount() {
        return LENGTH;
    }

    public static long calculatePurchasableCount(Money money) {
        long count = money.calculatePurchasableCount(PRICE);
        if (count <= 0L) {
            throw new IllegalArgumentException(
                    String.format("구매금액은 로또 가격 이상이어야 합니다. 로또 가격 : %d", PRICE)
            );
        }
        return count;
    }

    private void validate(Set<LottoNumber> lottoNumberSet) {
        if (lottoNumberSet.size() != LENGTH) {
            throw new IllegalArgumentException(
                    String.format("로또 숫자는 중복없이 %d개여야 합니다", LENGTH)
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

    @Override
    public String toString() {
        return this.numbers().toString();
    }

    public boolean contains(LottoNumber number) {
        return lottoNumberSet.contains(number);
    }
}
