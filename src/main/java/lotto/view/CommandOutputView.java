package lotto.view;

import lotto.domain.LottoNumber;
import lotto.domain.LottoStatus;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static lotto.domain.LottoStatus.*;
import static lotto.domain.LottoStatus.FIVE_CORRECT;
import static lotto.domain.LottoStatus.FIVE_CORRECT_BONUS;
import static lotto.domain.LottoStatus.SIX_CORRECT;

public class CommandOutputView implements OutputView {

    @Override
    public void printLog(List<LottoNumber> list) {
        String result = list.stream()
                .map(lottoNumber -> String.valueOf(lottoNumber.getNumber()))
                .collect(Collectors.joining(", ", "[", "]"));
        System.out.println(result);
    }

    @Override
    public void printPriceMessage() { System.out.println("구입금액을 입력해 주세요."); }

    @Override
    public void printLottoCountMessage(int manualLottoCount, int autoLottoCount) {
        System.out.println("수동으로 " + manualLottoCount + "장, 자동으로 " + autoLottoCount + "개를 구매했습니다.");
    }

    @Override
    public void printStatistics(Map<LottoStatus, Integer> statuses) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        System.out.println("3개 일치 (" + THREE_CORRECT.getPrice() + "원) - " + statuses.getOrDefault(THREE_CORRECT, 0) + "개");
        System.out.println("4개 일치 (" + FOUR_CORRECT.getPrice() + "원) - " + statuses.getOrDefault(FOUR_CORRECT, 0) + "개");
        System.out.println("5개 일치 (" + FIVE_CORRECT.getPrice() + "원) - " + statuses.getOrDefault(FIVE_CORRECT, 0) + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (" + FIVE_CORRECT_BONUS.getPrice() + "원) - " + statuses.getOrDefault(FIVE_CORRECT_BONUS, 0) + "개");
        System.out.println("6개 일치 (" + SIX_CORRECT.getPrice() + "원) - " + statuses.getOrDefault(SIX_CORRECT, 0) + "개");
    }

    @Override
    public void printProfitRate(double profitRate) {
        System.out.println("총 수익률은 " + String.format("%.2f", profitRate) + "입니다.");
    }

    @Override
    public void printWinningLottoMessage() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    @Override
    public void printBonusNumberMessage() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }

    @Override
    public void printManualLottoCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
    }

    @Override
    public void printManualLottoInputMessage() {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
    }
}
