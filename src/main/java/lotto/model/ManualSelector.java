package lotto.model;

import java.util.Arrays;
import java.util.List;

public class ManualSelector {

    public LottoTicket buyTicket(Wallet wallet, List<LottoNumber> lottoNumbers) {
        wallet.spend(LottoTicket.PRICE);
        return new LottoTicket(lottoNumbers);
    }

    public LottoTicket buyTicket(Wallet wallet, Integer... numbers) {
        wallet.spend(LottoTicket.PRICE);
        return new LottoTicket(numbers);
    }

    public LottoTicket buyTicket(Wallet wallet, String numbers){
        wallet.spend(LottoTicket.PRICE);
        List<LottoNumber> list = Arrays.stream(numbers.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .map(LottoNumber::new)
                .toList();
        return new LottoTicket(list);
    }

}
