package lotto.domain;

import java.util.List;

public class LottoPlayer {

    private int price;
    private int lottoCount;
    private List<Lottos> lottos;

    public LottoPlayer(int price, int lottoCount, List<Lottos> lottos) {
        validatePrice(price);
        this.price = price;
        this.lottoCount = lottoCount;
        this.lottos = lottos;
    }

    private void validatePrice(int price) {
        if (price < 1000) {
            throw new IllegalArgumentException("1000원 미만의 구매 금액이 입력되었습니다.");
        }
    }

    public int getPrice() {
        return price;
    }

    public int getLottoCount() {
        return lottoCount;
    }

    public List<Lottos> getLottos() {
        return lottos;
    }
}
