package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTicketTest {

    @Test
    @DisplayName("정상적인 로또 생성")
    public void createTicket(){
        assertThatCode(()->new LottoTicket(1,2,3,4,5,6));
    }

    @Test
    @DisplayName("로또 번호 개수가 부족할 때")
    public void notEnoughNumber(){
        assertThatThrownBy(() -> new LottoTicket(1,2,3,4,5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 티켓에는 6개의 번호가 필요합니다.");
    }

    @Test
    @DisplayName("로또 번호에 중복이 있을 때")
    public void duplicateNumber(){
        assertThatThrownBy(() -> new LottoTicket(1,2,3,4,3,6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 티켓에는 6개의 번호가 필요합니다.");
    }

}