package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoTest {
    @Test
    void 숫자_6개_생성_테스트(){
        var numbers = IntStream.range(1, 7)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList());
        Lotto lotto = new Lotto(numbers);

        Assertions.assertThat(lotto.getLottoNumbersSize())
                .isEqualTo(6);
    }

    @Test
    void 중복_테스트(){
        var numbers = IntStream.range(1, 6)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList());

        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> new Lotto(numbers))
                .withMessage("로또번호가 6개가 아닙니다.");
    }

    @Test
    void 일치하는_개수_반환(){
        Lotto userLotto = testSetLotto(1,7);
        Lotto winningLotto = testSetLotto(4,10);
        Assertions.assertThat(userLotto.countMatchingNumbers(winningLotto)).isEqualTo(3);
    }

    @Test
    void 일치_없을때_0_반환(){
        Lotto userLotto = testSetLotto(1,7);
        Lotto winningLotto = testSetLotto(8,14);
        Assertions.assertThat(userLotto.countMatchingNumbers(winningLotto)).isEqualTo(0);
    }

    Lotto testSetLotto(int start, int end){
        return new Lotto(IntStream.range(start, end)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList()));
    }
}
