package calculator;

import java.util.Scanner;

public class CalculatorView {
	private final Scanner scanner = new Scanner(System.in);

	public String readLine() {
		System.out.println("더하고 싶은 숫자를 입력하세요.");
		return scanner.nextLine();
	}

	public void printLine(int number) {
		System.out.println(number);
	}
}
