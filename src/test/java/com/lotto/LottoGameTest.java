package com.lotto;

import com.lotto.model.LottoGame;
import com.lotto.model.LottoNumber;
import com.lotto.model.LottoRank;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoGameTest {

    private static final List<LottoNumber> DEFAULT_LOTTO_NUMBERS = Stream.of(1, 2, 3, 4, 5, 6)
            .map(LottoNumber::valueOf)
            .collect(Collectors.toList());

    @Test
    void 로또_게임을_생성할_수_있다() {
        LottoGame lottoGame = new LottoGame(1000, () -> DEFAULT_LOTTO_NUMBERS);
        assertThat(lottoGame).isNotNull();
    }

    @ParameterizedTest
    @CsvSource(value = {"1000-1", "1500-1", "2000-2", "2100-2"}, delimiter = '-')
    void 로또_게임을_생성할_떄_로또_티켓이_생성된다(int money, int expectedTicketSize) {
        LottoGame lottoGame = new LottoGame(money, () -> DEFAULT_LOTTO_NUMBERS);
        assertThat(lottoGame.getLottoTickets().size()).isEqualTo(expectedTicketSize);
    }

    @ParameterizedTest
    @ValueSource(ints = {999, 500, 0})
    void 로또_게임을_생성할_때_금액이_부족하면_예외가_발생한다(int money) {
        assertThatThrownBy(() -> new LottoGame(money, () -> DEFAULT_LOTTO_NUMBERS))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 구입_금액에_해당하는_로또를_구매할_수_있다() {
        LottoGame lottoGame = new LottoGame(14000, () -> DEFAULT_LOTTO_NUMBERS);
        assertThat(lottoGame.getLottoTickets().size()).isEqualTo(14);
    }

    @Test
    void 로또_게임의_수익률을_계산할_수_있다() {
        LottoGame lottoGame = new LottoGame(1000, () -> DEFAULT_LOTTO_NUMBERS);

        lottoGame.play(List.of(1, 2, 3, 4, 5, 6), 7);
        assertThat(lottoGame.calculateProfit()).isEqualTo((int) (LottoRank.FIRST.getPrize() / 1000));
    }
}
