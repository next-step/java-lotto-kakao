package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottosTest {

    @Test
    @DisplayName("merge는 두 묶음을 합친 새로운 Lottos를 반환한다.")
    void merge() {
        Lottos a = Lottos.from(List.of(Lotto.of(1, 2, 3, 4, 5, 6)));
        Lottos b = Lottos.from(List.of(
                Lotto.of(7, 8, 9, 10, 11, 12),
                Lotto.of(13, 14, 15, 16, 17, 18)
        ));

        Lottos merged = a.merge(b);

        assertThat(merged.size()).isEqualTo(3);
    }

}