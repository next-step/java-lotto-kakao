package domain;

import enumeration.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import strategy.AutoLottoNumberStrategy;

import java.util.List;

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

    @Test
    void Lottos의_복권_당첨에_따라_수령금을_반환() {
        Lottos lottos = Lottos.of(1, () -> List.of(1, 2, 3, 4, 5, 6));
        List<Rank> ranks = lottos.scratch(WinningLotto.of(List.of(1, 2, 3, 4, 5, 6), 7));
        assertThat(ranks.stream().mapToInt(Rank::prize).sum()).isEqualTo(Rank.FIRST.prize());
    }
}
