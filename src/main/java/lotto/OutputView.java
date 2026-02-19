package lotto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class OutputView {
	public void printPurchaseResult(LottoTickets lottoTickets, LottoPurchase purchase) {
		System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.%n", purchase.manualLottoCount().value(), purchase.autoLottoCount().value());
		lottoTickets.forEach(this::printLottoNumbers);
		System.out.println();
	}

	public void printStatistics(LottoStatistics lottoStatistics, Money purchaseMoney) {
		printStatisticsHeader();
		for (RankView rankView : RankView.winningViews()) {
			printRankResult(rankView, lottoStatistics.countOf(rankView.rank()));
		}
		printProfitRate(lottoStatistics.profitRate(purchaseMoney));
	}

	public void printError(String message) {
		System.out.printf("[ERROR] %s%n", message);
	}

	private ArrayList<Integer> formatNumbers(LottoNumbers lottoNumbers) {
		ArrayList<Integer> formattedNumbers = new ArrayList<>();
		for (LottoNumber lottoNumber : lottoNumbers.values()) {
			formattedNumbers.add(lottoNumber.getValue());
		}
		return formattedNumbers;
	}

	private void printLottoNumbers(LottoTicket lottoTicket) {
		System.out.println(formatNumbers(lottoTicket.getLottoNumbers()));
	}

	private void printStatisticsHeader() {
		System.out.println();
		System.out.println("당첨 통계");
		System.out.println("---------");
	}

	private void printRankResult(RankView rankView, int count) {
		System.out.printf("%s - %d개%n", rankView.description(), count);
	}

	private void printProfitRate(double profitRate) {
		System.out.printf("총 수익률은 %s입니다.%n", formatProfitRate(profitRate));
	}

	private String formatProfitRate(double profitRate) {
		return BigDecimal.valueOf(profitRate)
			.setScale(2, RoundingMode.DOWN)
			.toPlainString();
	}
}
