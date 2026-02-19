package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class LottoGameTest {

    private LottoGame lottoGame;

    @BeforeEach
    public void setUp() {
        lottoGame = new LottoGame();
    }

    @Test
    @DisplayName("로또 구매 금액을 전달하면 구매할 수 있는 로또 장수를 반환한다.")
    public void calculateLottoCountTest() {
        Assertions.assertThat(lottoGame.calculateTotalLottoCount(1500)).isEqualTo(1);
    }

    @Test
    @DisplayName("로또 구매 금액이 1000원 미만이면 예외를 반환한다.")
    public void calculateLottoCountExceptionTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> lottoGame.calculateTotalLottoCount(500));
        assertThat(exception.getMessage()).isEqualTo("1000원 이상의 금액을 입력해야 합니다.");
    }

    @Test
    @DisplayName("수동으로 구해마는 로또 수가 구입한 로또 수 보다 크면 예외를 반환한다.")
    public void calculateManualLottoCountExceptionTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> lottoGame.calculateManualLottoCount(3, 4));
        assertThat(exception.getMessage()).isEqualTo("구입한 로또 수 만큼만 수동으로 구매할 수 있습니다.");
    }

    @Test
    @DisplayName("구매 가능 장수를 넣으면 그에 맞는 사이즈의 로또 리스트를 반환한다.")
    public void purchaseLottoTest() {
        lottoGame.purchaseLotto(3, () -> List.of(1, 2, 3, 4, 5, 6));

        assertThat(lottoGame.getLottos().getLottoList().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("수동으로 로또를 구매하면 리스트를 반환한다.")
    public void purchaseManualLottoTest() {
        lottoGame.purchaseManualLotto("1, 2, 3, 4, 5, 6");

        assertThat(lottoGame.getLottos().getLottoList().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("정답 로또와 구매한 로또 하나를 비교하여 결과 Enum을 반환한다.")
    public void getOneLottoResultTest() {
        lottoGame.createWinningLotto("1, 2, 3, 4, 5, 6", "7");
        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        Lotto lotto3 = new Lotto(List.of(1, 2, 3, 4, 5, 8));
        Lotto lotto4 = new Lotto(List.of(1, 2, 3, 4, 9, 8));
        Lotto lotto5 = new Lotto(List.of(1, 2, 3, 10, 9, 8));

        lottoGame.getLottos().add(lotto1);
        lottoGame.getLottos().add(lotto2);
        lottoGame.getLottos().add(lotto3);
        lottoGame.getLottos().add(lotto4);
        lottoGame.getLottos().add(lotto5);
        GameResult gameResult = lottoGame.generateGameResult();

        assertThat(gameResult.countEnum(LottoRank.FIRST)).isEqualTo(1);
        assertThat(gameResult.countEnum(LottoRank.SECOND)).isEqualTo(1);
        assertThat(gameResult.countEnum(LottoRank.THIRD)).isEqualTo(1);
        assertThat(gameResult.countEnum(LottoRank.FOURTH)).isEqualTo(1);
        assertThat(gameResult.countEnum(LottoRank.FIFTH)).isEqualTo(1);
    }

    @Test
    @DisplayName("당첨 결과를 입력하면 당첨금 총액을 반환한다.")
    public void getLottoSumTest() {
        Lotto firstRankLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto thirdRankLotto = new Lotto(List.of(1, 2, 3, 4, 5, 8));

        lottoGame.createWinningLotto("1, 2, 3, 4, 5, 6", "7");
        lottoGame.getLottos().add(firstRankLotto);
        lottoGame.getLottos().add(thirdRankLotto);
        GameResult gameResult = lottoGame.generateGameResult();

        assertThat(gameResult.getLottoSum()).isEqualTo(2_001_500_000);
    }

    @Test
    @DisplayName("당첨 금액과 구매 수량을 넣으면 수익률을 반환한다.")
    public void getRateOfReturn() {
        Lotto firstRankLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto thirdRankLotto = new Lotto(List.of(1, 2, 3, 4, 5, 8));

        lottoGame.createWinningLotto("1, 2, 3, 4, 8, 9", "10");
        lottoGame.getLottos().add(firstRankLotto);
        lottoGame.getLottos().add(thirdRankLotto);
        GameResult gameResult = lottoGame.generateGameResult();

        assertThat(gameResult.getRateOfReturn()).isEqualTo(775.0);
    }
}
