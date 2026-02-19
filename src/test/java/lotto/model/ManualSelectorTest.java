package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;

class ManualSelectorTest {

    @Test
    @DisplayName("직접 LottoNumber 전달 생성")
    public void ManualLottoNumber() {
        ManualSelector manualSelector = new ManualSelector();
        Wallet wallet = new Wallet(1000);
        List<LottoNumber> lottoNumbers = List.of(
                LottoNumber.of(1),
                LottoNumber.of(2),
                LottoNumber.of(3),
                LottoNumber.of(4),
                LottoNumber.of(5),
                LottoNumber.of(6)
        );

        assertThatCode(() -> manualSelector.buyTicket(wallet, lottoNumbers))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("숫자 6개 전달 생성")
    public void ManualNumber() {
        ManualSelector manualSelector = new ManualSelector();
        Wallet wallet = new Wallet(1000);

        assertThatCode(() -> manualSelector.buyTicket(wallet, 1, 2, 3, 4, 5, 6))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("문자열 전달 생성")
    public void ManualString() {
        ManualSelector manualSelector = new ManualSelector();
        Wallet wallet = new Wallet(1000);

        assertThatCode(() -> manualSelector.buyTicket(wallet, "1,2,3 ,4, 5, 6"))
                .doesNotThrowAnyException();
    }

}