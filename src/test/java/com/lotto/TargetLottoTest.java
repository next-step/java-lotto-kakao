package com.lotto;

import com.lotto.model.LottoNumbers;
import com.lotto.model.LottoRank;
import com.lotto.model.TargetLotto;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class TargetLottoTest {

    @ParameterizedTest
    @CsvSource(value = {
            "1,2,3,4,5,6-FIRST",
            "1,2,3,4,5,7-SECOND",
            "1,2,3,4,5,8-THIRD",
            "1,2,3,4,8,9-FOURTH",
            "1,2,3,8,9,10-FIFTH",
            "1,2,8,9,10,11-FAIL"}, delimiter = '-')
    void 두_로또번호를_비교하여_등수를_결정할_수_있다(String input, LottoRank expect) {
        TargetLotto targetLotto = new TargetLotto(List.of(1, 2, 3, 4, 5, 6), 7);

        List<Integer> numbers = Stream.of(input.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        LottoNumbers lottoNumbers = LottoNumbers.valueOf(numbers);

        LottoRank lottoRank = targetLotto.match(lottoNumbers);
        assertThat(lottoRank).isEqualTo(expect);
    }
}