package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTest {

    @Test
    @DisplayName("myLotto 생성 테스트")
    void makeMyLotto() {
        int lottoNum = 10;
        LottoBalls dummyLotto = new LottoBalls(Set.of(1, 2, 3, 4, 5, 6));

        List<LottoBalls> list = Collections.nCopies(lottoNum, dummyLotto);

        MyLotto myLotto = new MyLotto(list);

        assertThat(myLotto.getSize()).isEqualTo(lottoNum);
    }
}
