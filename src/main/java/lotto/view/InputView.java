package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

import lotto.model.ticket.LottoNumber;
import lotto.model.common.Money;

public class InputView {

	private final Scanner scanner;

	public InputView() {
		scanner = new Scanner(System.in);
	}

	public Money readPurchasePrice() {
		System.out.println("구입금액을 입력해 주세요.");
		int purchasePrice = praseInt(scanner.nextLine());
		return new Money(purchasePrice);
	}

	public int readManualPurchaseTicketCount(){
		System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
		int ticketCount = praseInt(scanner.nextLine());
		validateManualCount(ticketCount);
		return ticketCount;
	}

	public List<List<LottoNumber>> readManualLottoNumbers(int count){
		if(count <=0) return List.of();

		System.out.println("수동으로 구매할 번호를 입력해 주세요.");
		return IntStream.range(0, count)
				.mapToObj(i -> parseNumbers(scanner.nextLine()))
				.toList();
	}

	public List<Integer> readWinningNormalNumbers() {
		System.out.println("지난 주 당첨 번호를 입력해 주세요.");
		String[] numbers = splitNumbersByDelimiter(scanner.nextLine(), ",");
		return Arrays.stream(numbers)
				.map(String::trim)
				.map(this::praseInt)
				.toList();
	}

	public LottoNumber readBonusNumber() {
		System.out.println("보너스 번호를 입력해 주세요.");
		int bonusNumber = praseInt(scanner.nextLine());
		return LottoNumber.of(bonusNumber);
	}

	private int praseInt(String input) {
		try {
			return Integer.parseInt(input);
		} catch (RuntimeException runtimeException) {
			throw new IllegalArgumentException("정확한 숫자를 입력해주세요.", runtimeException);
		}
	}

	private String[] splitNumbersByDelimiter(String input, String delimiter) {
		try {
			return input.split(delimiter);
		} catch (RuntimeException runtimeException) {
			throw new IllegalArgumentException("입력 형식을 지켜주세요. 예시) 1,2,3", runtimeException);
		}
	}

	private List<LottoNumber> parseNumbers(String input) {
		return Arrays.stream(splitNumbersByDelimiter(input, ","))
				.map(String::trim)
				.map(this::praseInt)
				.map(LottoNumber::of)
				.toList();
	}

	private void validateManualCount(int count) {
		if (count < 0) throw new IllegalArgumentException("수동 구매 수량은 0 이상이어야 합니다.");
	}
}
