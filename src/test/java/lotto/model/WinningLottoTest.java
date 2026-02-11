package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class WinningLottoTest {


    @Test
    @DisplayName("유효한 당첨로또 생성")
    public void createWinningLotto() {
        LottoNumber bonusNum = new LottoNumber(33); // 보넛스랑 answer 안겹치는니 확인필요
        assertThatCode(() -> new WinningLotto(new LottoTicket(1,2,3,4,5,6), bonusNum));
    }

    @Test
    @DisplayName("보너스 번호가 겹친 당첨로또")
    public void InvalidWinningLotto() {
        LottoNumber bonusNum = new LottoNumber(1);
        assertThatThrownBy(() -> new WinningLotto(new LottoTicket(1,2,3,4,5,6), bonusNum))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("로또 등수 판별(4등)")
    public void getWinningRank4(){
        WinningLotto winningLotto = new WinningLotto(new LottoTicket(1, 2, 3, 4, 5, 6), new LottoNumber(8));
        WinningRank rank = winningLotto.checkRank(new LottoTicket(3,4,5,6,7,8));
        assertThat(rank).isEqualTo(WinningRank.FOURTH);
    }

    @Test
    @DisplayName("로또 등수 판별(3등)")
    public void getWinningRank3(){
        WinningLotto winningLotto = new WinningLotto(new LottoTicket(1, 2, 3, 4, 5, 6), new LottoNumber(8));
        WinningRank rank = winningLotto.checkRank(new LottoTicket(3,4,5,6,2,9));
        assertThat(rank).isEqualTo(WinningRank.THIRD);
    }

    @Test
    @DisplayName("로또 등수 판별(2등)")
    public void getWinningRank2(){
        WinningLotto winningLotto = new WinningLotto(new LottoTicket(1, 2, 3, 4, 5, 6), new LottoNumber(8));
        WinningRank rank = winningLotto.checkRank(new LottoTicket(3,4,5,6,1,8));
        assertThat(rank).isEqualTo(WinningRank.SECOND);
    }
}