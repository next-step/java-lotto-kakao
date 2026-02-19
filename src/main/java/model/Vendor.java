package model;

import Interface.LottoGenerator;

public class Vendor {
    private final LottoGenerator lottoGenerator;

    public Vendor(LottoGenerator lottoGenerator) {
        this.lottoGenerator = lottoGenerator;
    }

    public Lottos sell(int money) {
        validateMoney(money);

        Lottos lottos = new Lottos();
        int count = money / 1000;

        for (int i = 0; i < count; i++) {
            lottos.add(lottoGenerator.issueLotto());
        }

        return lottos;
    }

    private void validateMoney(int money) {
        if (money % 1000 > 0) throw new RuntimeException("천원 단위의 금액을 입력해주세요.");
        if (money <= 0) throw new RuntimeException("금액은 0보다 커야 합니다");
    }
}