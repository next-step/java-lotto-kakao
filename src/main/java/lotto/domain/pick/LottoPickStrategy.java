package lotto.domain.pick;

import lotto.domain.LottoNumber;

import java.util.List;

public interface LottoPickStrategy {

    public List<LottoNumber> generate();
}
