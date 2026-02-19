package view;

import domain.lotto.Lotto;
import domain.lotto.LottoGroup;
import domain.lotto.LottoNumber;
import domain.winning.LottoResult;
import domain.winning.WinningStatus;

import java.util.List;
import java.util.Map;

public class OutputView {

    public OutputView() {
    }

    public void printLottoCount(LottoGroup lottoGroup) {
        System.out.println();
        System.out.println(lottoGroup.getSize() + "개를 구매했습니다.");
        for (Lotto lotto : lottoGroup.getLottoList()) {
            System.out.println(lotto);
        }
        System.out.println();
    }

    public void printStatistics(LottoResult lottoResult) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
        printWinningCount(lottoResult);
        printWinningRate(lottoResult);
    }

    private void printWinningCount(LottoResult lottoResult) {
        for (Map.Entry<WinningStatus, Integer> entry : lottoResult.getCounts().entrySet()) {
            String format = String.format("%d개 일치" + ((entry.getKey() == WinningStatus.SECOND) ? ", 보너스 볼 일치" : "") + " (%d원) - %d개",
                    entry.getKey().matchCount(),
                    entry.getKey().prize(),
                    entry.getValue());
            System.out.println(format);
        }
    }

    private void printWinningRate(LottoResult lottoResult) {
        double rate = lottoResult.totalRate();
        System.out.printf("총 수익률은 %.2f입니다.%n", rate);
        System.out.println((rate >= 1)
                ? "축하합니다! 이익이 발생했습니다(기준이 1이기 때문에 결과적으로 이득입니다.)"
                : "아쉽게도 손해입니다(기준이 1이기 때문에 결과적으로 손해입니다.)");
    }
}
