package lotto.model;

import java.util.Arrays;
import java.util.List;

public class ManualSelector {

    public static final Money LOTTO_PRICE = new Money(-1000);

    public LottoTicket buyTicket(Wallet wallet, List<LottoNumber> lottoNumbers) {
        wallet.change(LOTTO_PRICE);
        return new LottoTicket(lottoNumbers);
    }

    public LottoTicket buyTicket(Wallet wallet, Integer... numbers) {
        wallet.change(LOTTO_PRICE);
        return new LottoTicket(numbers);
    }

    public LottoTicket buyTicket(Wallet wallet, String numbers){
        wallet.change(LOTTO_PRICE);
        List<LottoNumber> list = Arrays.stream(numbers.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .map(LottoNumber::new)
                .toList();
        return new LottoTicket(list);
    }

}
