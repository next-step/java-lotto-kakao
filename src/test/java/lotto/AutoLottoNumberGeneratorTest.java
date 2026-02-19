package lotto;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.pick.AutoLottoNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AutoLottoNumberGeneratorTest {

    @Test
    @DisplayName("자동 생성기는 1~45 범위의 중복 없는 6개 번호를 오름차순으로 반환한다")
    void success_test() {
        AutoLottoNumberGenerator generator = new AutoLottoNumberGenerator();
        List<LottoNumber> result = generator.generate();

        assertThat(result).hasSize(Lotto.REQUIRED_SIZE);
        assertThat(result).doesNotHaveDuplicates();
        assertThat(result)
                .allSatisfy(n -> assertThat(n.getValue())
                        .isBetween(LottoNumber.MIN_LOTTO_NUMBER, LottoNumber.MAX_LOTTO_NUMBER));

        List<Integer> values = result.stream().map(LottoNumber::getValue).toList();
        assertThat(values).isSorted();
    }

}