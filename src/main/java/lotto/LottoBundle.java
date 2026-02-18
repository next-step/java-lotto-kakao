package lotto;

import money.Money;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LottoBundle {
    private final List<Lotto> lottos;

    public LottoBundle(Lotto first, Lotto... rest) {
        this(mergeToList(first, rest));
    }

    public LottoBundle(List<Lotto> lottos) {
        validate(lottos);
        this.lottos = List.copyOf(lottos);
    }

    private static List<Lotto> mergeToList(Lotto first, Lotto... rest) {
        if (first == null || rest == null) {
            throw new IllegalArgumentException("로또 묶음에 null이 포함될 수 없습니다.");
        }
        List<Lotto> list = new ArrayList<>();
        list.add(first);
        list.addAll(Arrays.asList(rest));
        return list;
    }

    public static LottoBundle buy(Money money) {
        return buy(money, new LottoGenerator());
    }

    static LottoBundle buy(Money money, LottoGenerator lottoGenerator) {
        PurchasePlan purchasePlan = PurchasePlan.from(money, List.of());
        return buy(purchasePlan, lottoGenerator);
    }

    public static LottoBundle buy(PurchasePlan purchasePlan) {
        return buy(purchasePlan, new LottoGenerator());
    }

    static LottoBundle buy(PurchasePlan purchasePlan, LottoGenerator lottoGenerator) {
        if (purchasePlan == null) {
            throw new IllegalArgumentException("구매 계획은 null일 수 없습니다.");
        }

        if (lottoGenerator == null) {
            throw new IllegalArgumentException("로또 생성기는 null일 수 없습니다.");
        }

        List<Lotto> lottos = new ArrayList<>(purchasePlan.getManualLottos());
        long count = purchasePlan.getAutoCount();
        for (long c = 0; c < count; c++) {
            lottos.add(lottoGenerator.generate());
        }
        return new LottoBundle(lottos);
    }

    private void validate(List<Lotto> lottos) {
        if (lottos == null || lottos.contains(null)) {
            throw new IllegalArgumentException("로또 묶음에 null이 포함될 수 없습니다.");
        }
    }

    public LottoBundleResult evaluate(WinLotto win) {
        LottoBundleResultBuilder lottoBundleResultBuilder = new LottoBundleResultBuilder();
        for (Lotto lotto : lottos) {
            LottoRank rank = win.lottery(lotto);
            lottoBundleResultBuilder.count(rank);
            lottoBundleResultBuilder.addFee(Money.won(Lotto.PRICE));
        }
        return lottoBundleResultBuilder.build();
    }

    public int size() {
        return lottos.size();
    }

    public List<Lotto> asList() {
        return List.copyOf(lottos);
    }
}
