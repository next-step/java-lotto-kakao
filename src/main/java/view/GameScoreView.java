package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameScoreView {
	private Scanner scanner = new Scanner(System.in);

	public void showInputWinNumberMessage() {
		System.out.println("지난 주 당첨 번호를 입력해 주세요.");
	}
	public List<Integer> inputWinNumber() {
		String rawNumbers = scanner.nextLine();
		List<Integer> result = new ArrayList<>();
		for (String rawNumber : rawNumbers.split(",")) {
			result.add(Integer.parseInt(rawNumber.trim()));
		}
		return result;
	}
	public void showInputBonusBall() {
		System.out.println("보너스 볼을 입력해 주세요.");
	}

	public Integer inputBonusBall() {
		String rawNumber = scanner.nextLine();
		return Integer.parseInt(rawNumber);
	}
}
