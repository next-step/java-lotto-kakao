package com.lotto;

import com.lotto.model.LottoRank;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LottoRankTest {

    @ParameterizedTest
    @CsvSource(value = {
            "6,false,FIRST",
            "5,true,SECOND",
            "5,false,THIRD",
            "4,false,FOURTH",
            "3,false,FIFTH",
            "3,true,FAIL",
            "2,false,FAIL",
            "2,true,FAIL",
            "1,false,FAIL",
            "1,true,FAIL",
            "0,false,FAIL",
            "0,true,FAIL"})
    void 일치하는_개수와_보너스_일치_여부로_로또_등수를_결정할_수_있다(int matchCount, boolean bonus, LottoRank expect) {
        LottoRank lottoRank = LottoRank.of(matchCount, bonus);
        assertEquals(expect, lottoRank);
    }
}