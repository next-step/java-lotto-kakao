package lotto.domain.pick;

import java.util.List;

public interface LottoPickStrategy {

    public static final int LOTTO_SIZE = 6;

    public List<Integer> generate();
}
