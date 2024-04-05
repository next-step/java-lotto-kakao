package lotto;

import lotto.model.LottoNumber;
import lotto.model.LottoRank;
import lotto.model.LottoTicket;
import lotto.model.WinningLottoTicket;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoTicketTest {

    @Test
    void 로또_티켓은_6개의_로또번호를_가진다() {
        assertDoesNotThrow(() -> createLottoTicket(1, 2, 3, 4, 5, 6));
    }

    @Test
    void 로또_티켓에_로또_번호의_개수가_6이_아니라면_예외를_던진다() {
        List<LottoNumber> lottoNumbers = List.of(new LottoNumber(1));

        assertThrows(IllegalArgumentException.class, () -> new LottoTicket(lottoNumbers));
    }

    @Test
    void 로또_번호가_중복이라면_예외를_던진다() {
        assertThrows(IllegalArgumentException.class, () -> createLottoTicket(1, 1, 2, 3, 4, 5));
    }

    @Test
    void 로또_번호는_오름차순으로_가져온다() {
        List<LottoNumber> lottoNumbers = List.of(
                new LottoNumber(6),
                new LottoNumber(5),
                new LottoNumber(3),
                new LottoNumber(2),
                new LottoNumber(1),
                new LottoNumber(16)
        );
        LottoTicket lottoTicket = new LottoTicket(lottoNumbers);

        assertThat(lottoTicket.getLottoNumbers()).isEqualTo(new HashSet<>(lottoNumbers));
    }

    @Test
    void 로또_당첨_랭크를_계산한다() {
        LottoTicket lottoTicket = createLottoTicket(1, 2, 3, 4, 5, 6);

        WinningLottoTicket winningLottoTicket = new WinningLottoTicket(createLottoTicket(1, 2, 3, 4, 5, 9), new LottoNumber(6));

        assertThat(winningLottoTicket.match(lottoTicket)).isEqualTo(LottoRank.SECOND);
    }

    @Test
    void contains는_티켓이_로또_번호를_포함하면_True를_리턴한다() {
        LottoTicket lottoTicket = createLottoTicket(1, 2, 3, 4, 5, 6);

        assertThat(lottoTicket.contains(new LottoNumber(1))).isTrue();
    }

    @Test
    void contains는_티켓이_로또_번호를_포함하지_않으면_False를_리턴한다() {
        LottoTicket lottoTicket = createLottoTicket(1, 2, 3, 4, 5, 6);

        assertThat(lottoTicket.contains(new LottoNumber(7))).isFalse();
    }

    private LottoTicket createLottoTicket(int... numbers) {
        return Arrays.stream(numbers)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.collectingAndThen(Collectors.toList(), LottoTicket::new));
    }
}
