package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import strategy.AutoLottoNumberStrategy;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Lottos 관련 테스트")
public class LottosTest {
    @Test
    void Lottos는_로또_다발_수와_로또_생성_방식을_주입받아_생성() {
        int bunchSize = 10;
        int numberLength = 7;
        Lottos lottos = Lottos.of(bunchSize, AutoLottoNumberStrategy.of(numberLength));
        assertThat(lottos).isNotNull();
        assertThat(lottos.bunch().size()).isEqualTo(bunchSize);
        lottos.bunch().forEach(e -> assertThat(e.numbers().size()).isEqualTo(numberLength));
    }
}
