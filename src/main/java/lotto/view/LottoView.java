package lotto.view;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import lotto.model.Lotto;
import lotto.model.MatchCount;
import lotto.model.Money;

public class LottoView {

	private final Scanner scanner = new Scanner(System.in);

	public Money readMoney() {
		System.out.println("구입금액을 입력해 주세요.");
		return new Money(Integer.parseInt(scanner.nextLine()));
	}

	public String readWinningNumbers() {
		System.out.println("지난 주 당첨 번호를 입력해 주세요.");
		return scanner.nextLine();
	}

	public int readManualPurchaseCount() {
		System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
		return Integer.parseInt(scanner.nextLine());
	}

	public List<String> readManualLottoNumbers(int manualCount) {
		if (manualCount == 0) {
			return List.of();
		}
		System.out.println("수동으로 구매할 번호를 입력해 주세요.");
		List<String> inputs = new ArrayList<>();
		for (int i = 0; i < manualCount; i++) {
			inputs.add(scanner.nextLine());
		}
		return inputs;
	}

	public int readBonus() {
		System.out.println("보너스 볼을 입력해 주세요.");
		return Integer.parseInt(scanner.nextLine());
	}

	public void printTickets(List<Lotto> tickets) {
		System.out.println(tickets.size() + "개를 구매했습니다.");
		tickets.forEach(ticket -> System.out.println(ticket.getNumbers()));
	}

	public void printTickets(int manualCount, int autoCount, List<Lotto> tickets) {
		System.out.println("수동으로 " + manualCount + "장, 자동으로 " + autoCount + "개를 구매했습니다.");
		tickets.forEach(ticket -> System.out.println(ticket.getNumbers()));
	}

	public void printStatistics(Map<MatchCount, Integer> counts, double returnRate) {
		System.out.println("당첨 통계");
		Arrays.stream(MatchCount.values())
			.filter(MatchCount::isWinningRank)
			.forEach(
				match -> System.out.println(
					match.statisticLine(counts.getOrDefault(match, 0))
				)
			);
		System.out.println("총 수익률은 " + returnRate + "입니다.");
	}
}
