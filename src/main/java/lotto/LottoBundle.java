package lotto;

import money.Money;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
        PurchasePlan purchasePlan = PurchasePlan.from(money, 0);
        return buy(purchasePlan, List.of(), lottoGenerator);
    }

    static LottoBundle buy(PurchasePlan purchasePlan, List<Lotto> manualLottos, LottoGenerator lottoGenerator) {
        if (purchasePlan == null) {
            throw new IllegalArgumentException("구매 계획은 null일 수 없습니다.");
        }
        if (manualLottos == null) {
            throw new IllegalArgumentException("수동 로또 목록은 null일 수 없습니다.");
        }
        if (purchasePlan.getManualCount() != manualLottos.size()) {
            throw new IllegalArgumentException("수동 구매 수량과 수동 로또 목록의 크기가 일치하지 않습니다.");
        }
        LottosGenerator lottosGenerator = new CompositeLottosGenerator(List.of(
                new ManualLottosGenerator(manualLottos),
                new AutoLottosGenerator(purchasePlan.getAutoCount(), lottoGenerator)
        ));
        return lottosGenerator.generate();
    }

    private void validate(List<Lotto> lottos) {
        if (lottos == null) {
            throw new IllegalArgumentException("로또 묶음에 null이 포함될 수 없습니다.");
        }
        validateElements(lottos);
    }

    private void validateElements(List<Lotto> lottos) {
        final String message = "로또 묶음에 null이 포함될 수 없습니다.";
        try {
            for (Lotto lotto : lottos) {
                Objects.requireNonNull(lotto, message);
            }
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(message);
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
