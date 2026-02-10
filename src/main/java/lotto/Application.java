package lotto;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Application {
	private static final String LOTTO_REGEX =
		"^(?:[1-9]|[1-3][0-9]|4[0-5])(?:,\\s*(?:[1-9]|[1-3][0-9]|4[0-5])){5}$";

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		System.out.println("구입금액을 입력해 주세요.");
		int budget = Integer.parseInt(scanner.nextLine());

		Buyer buyer = Buyer.buyLotteries(budget);
		List<Lotto> tickets = buyer.getTickets();
		System.out.println(tickets.size() + "개를 구매했습니다.");
		for (Lotto ticket : tickets) {
			System.out.println(ticket.getNumbers());
		}

		System.out.println("지난 주 당첨 번호를 입력해 주세요.");
		String winningLottoString = scanner.nextLine();
		if (!winningLottoString.matches(LOTTO_REGEX)) {
			throw new IllegalArgumentException("입력 형식이 잘못되었습니다.");
		}

		List<Integer> numbers = Arrays.stream(winningLottoString.split(","))
			.map(String::trim)
			.map(Integer::parseInt)
			.collect(Collectors.toList());

		if (new HashSet<>(numbers).size() != 6) {
			throw new IllegalArgumentException("당첨 번호는 중복될 수 없습니다.");
		}

		System.out.println("보너스 볼을 입력해 주세요.");
		int bonus = Integer.parseInt(scanner.nextLine());
		if (!(1 <= bonus && bonus <= 45)) {
			throw new IllegalArgumentException("보너스 볼은 1~45 사이의 숫자여야 합니다.");
		}
		WinningLotto winningLotto = new WinningLotto(numbers, bonus);

		LotteryChecker lotteryChecker = new LotteryChecker();
		for (Lotto ticket : tickets) {
			int count = winningLotto.checkNumbers(ticket);
			boolean isContainBonus = winningLotto.isContainBonus(ticket);
			MatchCount match = MatchCount.from(count, isContainBonus);
			if (match != null) {
				lotteryChecker.increase(match);
			}
		}

		printStatistics(lotteryChecker, budget);
	}

	private static void printStatistics(LotteryChecker checker, int budget) {
		Map<MatchCount, Integer> counts = checker.getCounts();
		System.out.println("당첨 통계");
		System.out.println("3개 일치 (5000원)- " + counts.get(MatchCount.THREE) + "개");
		System.out.println("4개 일치 (50000원)- " + counts.get(MatchCount.FOUR) + "개");
		System.out.println("5개 일치 (1500000원)- " + counts.get(MatchCount.FIVE) + "개");
		System.out.println("5개 일치, 보너스 볼 일치(30000000원)- " + counts.get(MatchCount.FIVE_BONUS) + "개");
		System.out.println("6개 일치 (2000000000원)- " + counts.get(MatchCount.SIX) + "개");
		int totalPrize = checker.calculateTotalPrize();
		System.out.println("총 수익률은 " + LotteryChecker.calculateReturnRate(totalPrize, budget) + "입니다.");
	}

}
