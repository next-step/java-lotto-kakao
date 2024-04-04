package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class WinningLottoTest {

    @ParameterizedTest
    @CsvSource(value = {"1,2,3,4,5,6;7"}, delimiter = ';')
    public void 당첨번호는_1이상_45이하의_중복되지_않는_수_6개와_보너스볼_1개로_이루어져_있다(String regularNumbers, int bonusNumber) {
        List<LottoNumber> regularBalls = parseNumbers(regularNumbers);
        LottoNumber bonusBall = LottoNumber.valueOf(bonusNumber);
        WinningLotto winningLotto = new WinningLotto(regularBalls, bonusBall);
        Assertions.assertThat(winningLotto).isInstanceOf(WinningLotto.class);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "1,2,3,4,6,6;7",
        "1,2,3,4,5,6;6",
        "1,2,3,4,5;6"
    }, delimiter = ';')
    public void 당첨번호는_1이상_45이하의_중복되지_않는_수_6개와_보너스볼_1개로_이루어져_있지_않으면_예외를_던진다(String regularNumbers, int bonusNumber) {
        List<LottoNumber> regularBalls = parseNumbers(regularNumbers);
        LottoNumber bonusBall = LottoNumber.valueOf(bonusNumber);
        Assertions.assertThatThrownBy(() -> new WinningLotto(regularBalls, bonusBall))
            .isInstanceOf(RuntimeException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1,2,3,4,5,6;1,2,45,44,43,42;11",
            "1,2,3,4,5,6;1,45,44,43,42,41;11",
            "1,2,3,4,5,6;45,44,43,42,41,40;11"
    }, delimiter = ';')
    public void 로또_번호와_당첨_번호가_2개_이하로_매치될_경우_등외(String ticketNumbers, String winningNumbers, int bonusNumber){
        LottoTicket ticket = new LottoTicket(parseNumbers(ticketNumbers));
        WinningLotto winningLotto = new WinningLotto(parseNumbers(winningNumbers), LottoNumber.valueOf(bonusNumber));

        Prize prize = winningLotto.calculatePrize(ticket);

        Assertions.assertThat(prize).isEqualTo(Prize.NOTHING);
    }


    @ParameterizedTest
    @CsvSource(value = {
            "1,2,3,4,5,6;1,2,3,7,8,9;10"
    }, delimiter = ';')
    public void 로또_번호와_당첨_번호가_3개_매치될_경우_5등(String ticketNumbers, String winningNumbers, int bonusNumber){
        LottoTicket ticket = new LottoTicket(parseNumbers(ticketNumbers));
        WinningLotto winningLotto = new WinningLotto(parseNumbers(winningNumbers), LottoNumber.valueOf(bonusNumber));

        Prize prize = winningLotto.calculatePrize(ticket);

        Assertions.assertThat(prize).isEqualTo(Prize.FIFTH);
    }
    @ParameterizedTest
    @CsvSource(value = {
            "1,2,3,4,5,6;1,2,3,4,8,9;10"
    }, delimiter = ';')
    public void 로또_번호와_당첨_번호가_4개_매치될_경우_4등(String ticketNumbers, String winningNumbers, int bonusNumber){
        LottoTicket ticket = new LottoTicket(parseNumbers(ticketNumbers));
        WinningLotto winningLotto = new WinningLotto(parseNumbers(winningNumbers), LottoNumber.valueOf(bonusNumber));

        Prize prize = winningLotto.calculatePrize(ticket);

        Assertions.assertThat(prize).isEqualTo(Prize.FOURTH);
    }
    @ParameterizedTest
    @CsvSource(value = {
            "1,2,3,4,5,6;1,2,3,4,5,9;10"
    }, delimiter = ';')
    public void 로또_번호와_당첨_번호가_5개_매치되고_보너스_번호는_매치되지_않는_경우_3등(String ticketNumbers, String winningNumbers, int bonusNumber){
        LottoTicket ticket = new LottoTicket(parseNumbers(ticketNumbers));
        WinningLotto winningLotto = new WinningLotto(parseNumbers(winningNumbers), LottoNumber.valueOf(bonusNumber));

        Prize prize = winningLotto.calculatePrize(ticket);

        Assertions.assertThat(prize).isEqualTo(Prize.THIRD);
    }
    @ParameterizedTest
    @CsvSource(value = {
            "1,2,3,4,5,6;1,2,3,4,5,9;6"
    }, delimiter = ';')
    public void 로또_번호와_당첨_번호가_5개_매치되고_보너스_번호도_매치되는_경우_2등(String ticketNumbers, String winningNumbers, int bonusNumber){
        LottoTicket ticket = new LottoTicket(parseNumbers(ticketNumbers));
        WinningLotto winningLotto = new WinningLotto(parseNumbers(winningNumbers), LottoNumber.valueOf(bonusNumber));

        Prize prize = winningLotto.calculatePrize(ticket);

        Assertions.assertThat(prize).isEqualTo(Prize.SECOND);
    }
    @ParameterizedTest
    @CsvSource(value = {
            "1,2,3,4,5,6;1,2,3,4,5,6;10"
    }, delimiter = ';')
    public void 로또_번호와_당첨_번호가_6개_매치될_경우_1등(String ticketNumbers, String winningNumbers, int bonusNumber){
        LottoTicket ticket = new LottoTicket(parseNumbers(ticketNumbers));
        WinningLotto winningLotto = new WinningLotto(parseNumbers(winningNumbers), LottoNumber.valueOf(bonusNumber));

        Prize prize = winningLotto.calculatePrize(ticket);

        Assertions.assertThat(prize).isEqualTo(Prize.FIRST);
    }

    private static List<LottoNumber> parseNumbers(String numbers) {
        return Arrays.stream(numbers.split(","))
                .map(Integer::parseInt)
                .map(LottoNumber::valueOf)
                .collect(Collectors.toList());
    }
}
