package lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import lotto.enums.LottoStatus;

public class LottoView {
	private final Scanner scanner = new Scanner(System.in);

	private static void printDetailResult(LottoResult result, List<LottoStatus> winStatuses) {
		for (LottoStatus status : winStatuses) {
			String format = "%d개 일치 (%,d원) - %d개%n";

			if (status == LottoStatus.SIX_BONUS) {
				format = "%d개 일치, 보너스 볼 일치 (%,d원) - %d개%n";
			}
			System.out.printf(format, status.getCount(), status.getMoney(), result.get(status));
		}
	}

	private static int getManualCount(String count) {
		int manualCount;
		try {
			manualCount = Integer.parseInt(count);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("숫자 값을 입력해야 합니다.");
		}
		return manualCount;
	}

	public String readPrice() {
		return read("구입금액을 입력해주세요.");
	}

	public String readManualCount() {
		return read("수동으로 구매할 로또 수를 입력해 주세요.");
	}

	public void printPurchasedLotto(long manualCount, List<Lotto> lottos) {
		print("수동으로 " + manualCount + "장, 자동으로 " + (lottos.size() - manualCount) + "개를 구매했습니다.");
		for (Lotto lotto : lottos) {
			print(lotto.toString());
		}
		print("");
	}

	public String readPreviousLotto() {
		return read("지난 주 당첨 번호를 입력해 주세요.");
	}

	public Lottos readManualLottos(String count, Parser parser) {
		int manualCount = getManualCount(count);
		if (manualCount != 0) {
			print("수동으로 구매할 번호를 입력해 주세요.");
		}
		Lottos manualLottoList = new Lottos();
		for (int i = 0; i < manualCount; i++) {
			addLotto(parser, manualLottoList);
		}
		return manualLottoList;
	}

	private void addLotto(Parser parser, Lottos manualLottoList) {
		String manualLottoNumbers = scanner.nextLine();
		List<Ball> manualLottoBalls = parser.parse(manualLottoNumbers);
		Lotto maualLotto = new Lotto(manualLottoBalls);
		manualLottoList.add(maualLotto);
	}

	public String readPreviousBonusBall() {
		return read("보너스 볼을 입력해 주세요.");
	}

	public void printResult(User user) {

		List<LottoStatus> winStatuses = new ArrayList<>(
			List.of(LottoStatus.THREE, LottoStatus.FOUR, LottoStatus.FIVE, LottoStatus.SIX_BONUS, LottoStatus.SIX));

		print("당첨 통계");
		print("---------");
		printDetailResult(user.getResult(), winStatuses);
		System.out.printf("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)",
			(float)(user.getAward()) / (float)(user.getPrice()));
	}

	public void print(String message) {
		System.out.println(message);
	}

	private String read(String message) {
		print(message);
		return scanner.nextLine();
	}

}
