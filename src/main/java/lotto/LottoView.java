package lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import lotto.enums.LottoStatus;

public class LottoView {
	private final Scanner scanner = new Scanner(System.in);

	private static void printDetailResult(Map<LottoStatus, Integer> result, List<LottoStatus> winStatuses) {
		for (LottoStatus status : winStatuses) {
			String format = "%d개 일치 (%,d원) - %d개%n";

			if (status == LottoStatus.SIX_BONUS) {
				format = "%d개 일치, 보너스 볼 일치 (%,d원) - %d개%n";
			}
			System.out.printf(format, status.getCount(), status.getMoney(), result.getOrDefault(status, 0));
		}
	}

	public String readPrice() {
		String line = read("구입금액을 입력해주세요.");
		print("");
		return line;
	}

	public String readManualLottoCount() {
		String line = read("수동으로 구매할 로또 수를 입력해 주세요.");
		print("");
		return line;
	}

	public List<String> readManualLottos(int count) {
		print("수동으로 구매할 로또 번호를 입력해 주세요.");
		List<String> manualLottos = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			manualLottos.add(read(""));
		}
		print("");
		return manualLottos;
	}

	public void printPurchasedLotto(List<Lotto> lottos) {
		print(lottos.size() + "개를 구매했습니다.");
		for (Lotto lotto : lottos) {
			print(lotto.toString());
		}
		print("");
	}

	public void printPurchasedLotto(List<Lotto> lottos, int manualCount, int autoCount) {
		System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.\n", manualCount, autoCount);
		for (Lotto lotto : lottos) {
			print(lotto.toString());
		}
		print("");
	}

	public String readPreviousLotto() {
		return read("지난 주 당첨 번호를 입력해 주세요.");
	}

	public String readPreviousBonusBall() {
		String line = read("보너스 볼을 입력해 주세요.");
		print("");
		return line;
	}

	public void printResult(User user) {

		List<LottoStatus> winStatuses = new ArrayList<>(
			List.of(LottoStatus.THREE, LottoStatus.FOUR, LottoStatus.FIVE, LottoStatus.SIX_BONUS, LottoStatus.SIX));

		print("당첨 통계");
		print("---------");
		printDetailResult(user.getResult(), winStatuses);

		double profitRate = (double)(user.getAward()) / (double)(user.getPrice());
		String message;
		if (profitRate < 1.0) {
			message = String.format("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)", profitRate);
		} else if (profitRate > 1.0) {
			message = String.format("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 이득이라는 의미임)", profitRate);
		} else {
			message = String.format("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손익분기라는 의미임)", profitRate);
		}
		print(message);
	}

	public void print(String message) {
		System.out.println(message);
	}

	private String read(String message) {
		print(message);
		return scanner.nextLine();
	}

}
