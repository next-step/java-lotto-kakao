package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


public class LottoTicketTest {

    @Test
    public void compare는_중복되는_Number의_개수를_반환한다() {
        // given
        LottoTicket numbers = new LottoTicket(Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::valueOf)
                .collect(Collectors.toSet()));
        LottoTicket otherNumbers = new LottoTicket(Stream.of(1, 2, 3, 7, 8, 9)
                .map(LottoNumber::valueOf)
                .collect(Collectors.toSet()));

        // when
        int count = numbers.compare(otherNumbers);

        // then
        Assertions.assertThat(count).isEqualTo(3);
    }

    @Test
    public void contains는_해당Number의_포함여부를_반환한다() {
        // given
        LottoTicket numbers = new LottoTicket(Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::valueOf)
                .collect(Collectors.toSet()));
        LottoNumber lottoNumber = LottoNumber.valueOf(1);

        // when
        boolean isContaining = numbers.contains(lottoNumber);

        // then
        Assertions.assertThat(isContaining).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5,6"})
    public void 로또_번호는_중복되지_않는_6개의_수로_이루어져_있다(String numbers) {
        List<LottoNumber> lottoNumberList = parseNumbers(numbers);
        LottoTicket lottoTicket = new LottoTicket(lottoNumberList);
        Assertions.assertThat(lottoTicket).isInstanceOf(LottoTicket.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,6,6"})
    public void 로또_번호가_중복되는_경우_예외를_던진다(String numbers) {
        List<LottoNumber> lottoNumberList = parseNumbers(numbers);
        Assertions.assertThatThrownBy(() -> new LottoTicket(lottoNumberList)).isInstanceOf(RuntimeException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5", "1,2,3,4,5,6,7"})
    public void 로또_번호가_6개가_아닌_경우_예외를_던진다(String numbers) {
        List<LottoNumber> lottoNumberList = parseNumbers(numbers);
        Assertions.assertThatThrownBy(() -> new LottoTicket(lottoNumberList)).isInstanceOf(RuntimeException.class);
    }

    private static List<LottoNumber> parseNumbers(String numbers) {
        return Arrays.stream(numbers.split(","))
            .map(Integer::parseInt)
            .map(LottoNumber::valueOf)
            .collect(Collectors.toList());
    }

}
