package lotto.support;

import lotto.domain.LottoNumber;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoTestFixture {

    public static Set<LottoNumber> lottoNumbers(Integer... nums) {
        return Arrays.stream(nums)
                .map(LottoNumber::new)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
