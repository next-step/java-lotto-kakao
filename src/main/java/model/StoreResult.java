package model;

import java.util.*;
import java.util.stream.Collectors;

public class StoreResult {
	private static final int LOTTO_NUMBER_COUNT = 6;

	private final Result result;

	public StoreResult(){
		result = new Result();
	}

	public List<Integer> setNumber(String input) {
		List<Integer> numbers = parseWinningNumbers(input);

		if(numbers.size() != LOTTO_NUMBER_COUNT) throw new RuntimeException("당첨 번호는 6개의 숫자로 이루어져야 합니다");

		List<LottoNumber> lottoNumbers = numbers.stream()
			.map(this::toWinningLottoNumber)
			.collect(Collectors.toList());

		Set<LottoNumber> set = new HashSet<>();
		lottoNumbers.stream()
				.filter(n -> !set.add(n))
				.findFirst()
				.ifPresent(n -> {
					throw new IllegalArgumentException("당첨 번호는 중복될 수 없습니다");
				});

		result.setNumbers(lottoNumbers.stream().map(LottoNumber::getValue).collect(Collectors.toList()));
		return result.getNumbers();
	}

	private List<Integer> parseWinningNumbers(String input) {
		try {
			return Arrays.stream(input.split(","))
					.map(String::trim)
					.map(Integer::parseInt)
					.collect(Collectors.toList());
		} catch (NumberFormatException e) {
			throw new RuntimeException("당첨 번호는 1 ~ 45 사이의 양수로 이루어져야 합니다.");
		}
	}

	public Integer setBonus(String input) {
		try {
			LottoNumber bonus = toBonusLottoNumber(input);
			if (result.getNumbers().contains(bonus.getValue())) throw new RuntimeException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
			result.setBonus(bonus.getValue());
			return result.getBonus();
		}
		catch (NumberFormatException e) {
			throw new RuntimeException("보너스 번호는 1 ~ 45 사이의 양수로 이루어져야 합니다.");
		}
	}

	private LottoNumber toWinningLottoNumber(Integer value) {
		try {
			return LottoNumber.of(value);
		} catch (RuntimeException e) {
			throw new RuntimeException("당첨 번호는 1 ~ 45 사이의 양수로 이루어져야 합니다.");
		}
	}

	private LottoNumber toBonusLottoNumber(String input) {
		Integer parsedInt = Integer.parseInt(input);
		try {
			return LottoNumber.of(parsedInt);
		} catch (RuntimeException e) {
			throw new RuntimeException("보너스 번호는 1 ~ 45 사이의 양수로 이루어져야 합니다.");
		}
	}

	public Integer size(){
		return this.result.size();
	}

	public Result getResult() {
		return this.result;
	}
}
