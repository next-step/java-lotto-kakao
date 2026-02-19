package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class LottoParserTest {
    private static final String LOTTO_DELIMITER_MESSAGE =
            "로또 번호 6개는 쉼표(,)로 구분해 입력해 주세요. 예: 1, 2, 3, 4, 5, 6";
    private static final String LOTTO_NON_NUMERIC_MESSAGE =
            "로또 번호는  1~45 사이의 숫자만 입력해 주세요.";

    @Test
    void 쉼표_구분_번호를_파싱() {
        LottoParser lottoParser = new LottoParser();
        Lotto lotto = lottoParser.parse("1,2,3,4,5,6");

        Assertions.assertThat(lotto.mapToSortedNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    void 숫자가_아닌_값이면_예외() {
        LottoParser lottoParser = new LottoParser();

        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> lottoParser.parse("1,2,a,4,5,6"))
                .withMessage(LOTTO_NON_NUMERIC_MESSAGE);
    }

    @Test
    void 구분자가_잘못되면_예외() {
        LottoParser lottoParser = new LottoParser();

        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> lottoParser.parse("1,2,,4,5,6"))
                .withMessage(LOTTO_DELIMITER_MESSAGE);
    }
}
