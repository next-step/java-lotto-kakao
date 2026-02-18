package utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InputParserTest {
    @Test
    void parseSuccessMoneyFormat() {
        assertDoesNotThrow(() -> InputParser.parseMoney("14000"));
    }

    @Test
    void parseSuccessLottoFormat() {
        assertDoesNotThrow(() -> InputParser.parseLottoFormat("1, 2, 3, 4, 5, 6"));
        assertDoesNotThrow(() -> InputParser.parseLottoFormat("1,2,3,4,5,6,7,8,9"));
    }

    @Test
    void parseSuccessBonusNumberFormat() {
        assertDoesNotThrow(() -> InputParser.parseBonusNumberFormat("100"));
    }

    @Test
    void parseSuccessManualCountFormat() {
        assertDoesNotThrow(() -> InputParser.parseManualCount("0"));
        assertDoesNotThrow(() -> InputParser.parseManualCount("3"));
    }

    @Test
    @DisplayName("구입금액은 반드시 숫자여야 한다.")
    void validateMoneyInputFormat() {
        assertThrows(IllegalArgumentException.class,
                () -> InputParser.parseBonusNumberFormat("f"));
        assertThrows(IllegalArgumentException.class,
                () -> InputParser.parseBonusNumberFormat("."));
        assertThrows(IllegalArgumentException.class,
                () -> InputParser.parseBonusNumberFormat("42fdsf2344"));
    }

    @Test
    @DisplayName("로또 입력은 반드시 숫자와 ','로 이루어져야 한다.")
    void validateLottoInputFormat() {
        assertThrows(IllegalArgumentException.class,
                () -> InputParser.parseLottoFormat("1, b, 2, d"));
        assertThrows(IllegalArgumentException.class,
                () -> InputParser.parseLottoFormat("1s2d3f4"));
        assertThrows(IllegalArgumentException.class,
                () -> InputParser.parseLottoFormat(""));
    }

    @Test
    @DisplayName("보너스 숫자 입력은 반드시 숫자여야 한다.")
    void validateBonusNumberInputFormat() {
        assertThrows(IllegalArgumentException.class,
                () -> InputParser.parseBonusNumberFormat("d"));
        assertThrows(IllegalArgumentException.class,
                () -> InputParser.parseBonusNumberFormat("."));
        assertThrows(IllegalArgumentException.class,
                () -> InputParser.parseBonusNumberFormat(""));
    }

    @Test
    @DisplayName("수동 구매 수량은 0 이상의 숫자여야 한다.")
    void validateManualCountInputFormat() {
        assertThrows(IllegalArgumentException.class,
                () -> InputParser.parseManualCount("d"));
        assertThrows(IllegalArgumentException.class,
                () -> InputParser.parseManualCount(""));
        assertThrows(IllegalArgumentException.class,
                () -> InputParser.parseManualCount("-1"));
    }
}
