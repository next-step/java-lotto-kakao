package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;

class ManualSelectorTest {

    @Test
    @DisplayName("직접 LottoNumber 전달 생성")
    public void ManualLottoNumber(){
        ManualSelector manualSelector = new ManualSelector();
        Wallet wallet = new Wallet(1000);
        List<LottoNumber> lottoNumbers = List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        );

        assertThatCode(() -> manualSelector.buyTicket(wallet, lottoNumbers))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("숫자 6개 전달 생성")
    public void ManualNumber(){
        ManualSelector manualSelector = new ManualSelector();
        Wallet wallet = new Wallet(1000);

        assertThatCode(() -> manualSelector.buyTicket(wallet, 1,2,3,4,5,6))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("문자열 전달 생성")
    public void ManualString(){
        ManualSelector manualSelector = new ManualSelector();
        Wallet wallet = new Wallet(1000);

        assertThatCode(() -> manualSelector.buyTicket(wallet, "1,2,3 ,4, 5, 6"))
                .doesNotThrowAnyException();
    }

}