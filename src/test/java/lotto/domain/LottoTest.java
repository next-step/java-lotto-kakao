package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTest {

    @Test
    @DisplayName("myLotto 생성 테스트")
    void makeMyLotto() {
        int lottoNum = 10;
        MyLotto myLotto = new MyLotto(lottoNum);

        assertThat(myLotto.getSize()).isEqualTo(lottoNum);
    }
}
