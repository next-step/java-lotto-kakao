package lotto.domain;

public class LottoPlayer {

    private Money price;
    private Lottos lottos;

    public LottoPlayer(Money price, Lottos lottos) {
        this.price = price;
        this.lottos = lottos;
    }

    public static LottoPlayer of(Money price, Lottos lottos) {
        return new LottoPlayer(price, lottos);
    }

    public Money getPrice() {
        return price;
    }

    public LottoCount getLottoCount() {
        return LottoCount.of(lottos.size());
    }

    public Lottos getLottos() {
        return lottos;
    }
}
