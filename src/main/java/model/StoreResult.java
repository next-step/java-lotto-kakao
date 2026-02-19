package model;

import java.util.*;
import java.util.stream.Collectors;

public class StoreResult {
	Result result;

	public StoreResult(){
		result = new Result();
	}

	public List<Integer> setNumber(String input) {
		List<Integer> numbers = Arrays.stream(input.split(","))
			.map(String::trim)
			.map(Integer::parseInt)
			.collect(Collectors.toList());

		if(numbers.size() != LottoNumber.LOTTO_SIZE) throw new RuntimeException("당첨 번호는 "+LottoNumber.LOTTO_SIZE+"개의 숫자로 이루어져야 합니다");

		numbers.forEach(n -> Optional.of(n).filter(v -> (LottoNumber.MIN_NUMBER <= v && v <= LottoNumber.MAX_NUMBER)).orElseThrow(() -> new RuntimeException("당첨 번호는 "+ LottoNumber.MIN_NUMBER +"~"+LottoNumber.MAX_NUMBER+" 사이의 수로 이루어져야 합니다.")));

		Set<Integer> set = new HashSet<>();
		numbers.stream()
				.filter(n -> !set.add(n))
				.findFirst()
				.ifPresent(n -> {
					throw new RuntimeException("당첨 번호는 중복될 수 없습니다");
				});

		result.setNumbers(numbers);
		return result.getNumbers();
	}

	public Integer setBonus(String input) {
		try {
			Integer parsedInt = Integer.parseInt(input);
			if ( LottoNumber.MIN_NUMBER-1 >= parsedInt || parsedInt > LottoNumber.MAX_NUMBER ) throw new RuntimeException("보너스 번호는 "+ LottoNumber.MIN_NUMBER +"~"+LottoNumber.MAX_NUMBER+" 사이의 수로 이루어져야 합니다.");
			if (result.getNumbers().contains(parsedInt)) throw new RuntimeException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
			result.setBonus(parsedInt);
			return result.getBonus();
		}
		catch (NumberFormatException e) {
			throw new RuntimeException("보너스 번호는 "+ LottoNumber.MIN_NUMBER +"~"+LottoNumber.MAX_NUMBER+" 사이의 수로 이루어져야 합니다.");
		}
	}

	public Integer size(){
		return this.result.size();
	}

	public Result getResult() {
		return this.result;
	}
}
