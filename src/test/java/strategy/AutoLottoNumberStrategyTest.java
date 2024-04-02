package strategy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("AutoLottoNumberStrategy 관련 테스트")
class AutoLottoNumberStrategyTest {
    @ParameterizedTest
    @CsvSource(value = {"45", "6", "5"})
    void 주어진_길이만큼의_로또_숫자들을_생성(int numberLength) {
        LottoNumberStrategy strategy = AutoLottoNumberStrategy.of(numberLength);
        assertThat(strategy.perform().size()).isEqualTo(numberLength);
    }

    @Test
    void 생성할_수_있는_로또_숫자들의_길이를_초과한_경우_RuntimeException을_발생() {
        assertThatCode(() -> AutoLottoNumberStrategy.of(100)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_숫자들이_범위_내의_난수로_생성() {
        LottoNumberStrategy strategy = AutoLottoNumberStrategy.of(6);
        strategy.perform().forEach(e -> assertThat(e).isGreaterThanOrEqualTo(1).isLessThanOrEqualTo(45));
    }

}