package purchase;

import lotto.LottoBundle;
import money.Money;

public record LottoBundlePurchase(LottoBundle lottoBundle, Money paid, Money change) {
}
