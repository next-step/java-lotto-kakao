package utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoInputParserTest {
    @Test
    void parseSuccessLotto() {
        assertDoesNotThrow(() -> LottoInputParser.parseLottoFormat("1, 2, 3, 4, 5, 6"));
        assertDoesNotThrow(() -> LottoInputParser.parseLottoFormat("1,2,3,4,5,6,7,8,9"));
    }

    @Test
    void parseSuccessBonusNumber() {
        assertDoesNotThrow(() -> LottoInputParser.parseBonusNumberFormat("100"));
    }

    @Test
    @DisplayName("로또 입력은 반드시 숫자와 ','로 이루어져야 한다.")
    void validateLottoInputFormat() {
        assertThrows(IllegalArgumentException.class,
                () -> LottoInputParser.parseLottoFormat("1, b, 2, d"));
        assertThrows(IllegalArgumentException.class,
                () -> LottoInputParser.parseLottoFormat("1s2d3f4"));
        assertThrows(IllegalArgumentException.class,
                () -> LottoInputParser.parseLottoFormat(""));
    }

    @Test
    @DisplayName("보너스 숫자 입력은 반드시 숫자여야 한다.")
    void validateBonusNumberInputFormat() {
        assertThrows(IllegalArgumentException.class,
                () -> LottoInputParser.parseBonusNumberFormat("d"));
        assertThrows(IllegalArgumentException.class,
                () -> LottoInputParser.parseBonusNumberFormat("."));
        assertThrows(IllegalArgumentException.class,
                () -> LottoInputParser.parseBonusNumberFormat(""));
    }
}
