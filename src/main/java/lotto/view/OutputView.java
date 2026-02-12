package lotto.view;

import java.util.List;
import java.util.Map;

import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.LottoStatistics;

public class OutputView {
	public void printLottos(List<Lotto> lottos, int manualCount, int autoCount) {
		System.out.println(String.format("수동으로 %d장, 자동으로 %d개를 구매했습니다.", manualCount, autoCount));
		for (Lotto lotto : lottos) {
			System.out.println(lotto.getNumbers());
		}
		System.out.println();
	}

	public void printStatistics(LottoStatistics statistics) {
		System.out.println("당첨 통계");
		System.out.println("---------");
		Map<LottoResult, Integer> counts = statistics.getCounts();
		for (LottoResult result : LottoResult.values()) {
			System.out.println(formatResultLine(result, counts.get(result)));
		}
	}

	public void printProfitRate(double profitRate) {
		System.out.println("총 수익률은 " + formatRate(profitRate) + "입니다.");
	}

	private String formatResultLine(LottoResult result, int count) {
		if (result == LottoResult.FIVE_MATCH_WITH_BONUS) {
			return String.format("5개 일치, 보너스 볼 일치(%d원) - %d개", result.getPrize(), count);
		}
		return String.format("%d개 일치 (%d원)- %d개", result.getMatchCount(), result.getPrize(), count);
	}

	private String formatRate(double profitRate) {
		return String.format("%.2f", profitRate);
	}
}
