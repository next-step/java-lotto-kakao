package domain.lotto;

import java.util.List;

public class LottoFactory {

    public Lotto create(LottoGenerator lottoGenerator) {
        List<LottoNumber> lottoNumbers = lottoGenerator.generate().stream()
                .map(LottoNumber::new)
                .toList();
        return new Lotto(lottoNumbers);
    }
}
