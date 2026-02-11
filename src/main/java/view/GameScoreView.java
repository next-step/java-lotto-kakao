package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameScoreView {
	private final Scanner scanner = new Scanner(System.in);

	public void showInputWinNumberMessage() {
		System.out.println("지난 주 당첨 번호를 입력해 주세요.");
	}

	public List<Integer> inputWinNumber() {
		String rawNumbers = scanner.nextLine();
		try {
			List<Integer> result = new ArrayList<>();
			for (String rawNumber : rawNumbers.split(",")) {
				result.add(Integer.parseInt(rawNumber.trim()));
			}

			if(result.size() != 6) {
				throw new IllegalArgumentException("숫자 6개를 입력해주세요.");
			}
			return result;
		} catch (Exception e) {
			throw new IllegalArgumentException("구분자는 ','로 작성해주세요.");
		}
	}

	public void showInputBonusBall() {
		System.out.println("보너스 볼을 입력해 주세요.");
	}

	public Integer inputBonusBall() {
		try {
			String rawNumber = scanner.nextLine();
			return Integer.parseInt(rawNumber);
		} catch (Exception e) {
			throw new IllegalArgumentException("숫자를 입력하세요!");
		}
	}

	public void showErrorMessage(IllegalArgumentException e) {
		System.out.println("[Error] " + e.getMessage());
	}
}
