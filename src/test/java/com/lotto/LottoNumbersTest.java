package com.lotto;

import com.lotto.model.LottoNumbers;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoNumbersTest {
    @Test
    void 로또_번호는_중복될_수_없다() {
        assertThatThrownBy(() -> LottoNumbers.valueOf(List.of(1, 1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"1,2,3,4,5,6-6",
            "1,2,3,4,5,7-5",
            "1,2,3,4,7,8-4",
            "1,2,3,7,8,9-3",
            "1,2,7,8,9,10-2",
            "1,7,8,9,10,11-1",
            "7,8,9,10,11,12-0"}, delimiter = '-')
    void 로또_번호와_일치하는_갯수를_찾을_수_있다(String input, int expect) {
        List<Integer> numbers = Stream.of(input.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        LottoNumbers lottoNumbers = LottoNumbers.valueOf(numbers);
        LottoNumbers targetLottoNumbers = LottoNumbers.valueOf(List.of(1, 2, 3, 4, 5, 6));

        int matchCount = lottoNumbers.getMatchCount(targetLottoNumbers);
        Assertions.assertThat(matchCount).isEqualTo(expect);
    }
}