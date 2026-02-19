package model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import Interface.LottoGenerator;

public class ManualLottoGenerator implements LottoGenerator {
	private final List<Integer> numbers;

	public ManualLottoGenerator(List<Integer> numbers) {
		this.numbers = numbers;
	}

	public ManualLottoGenerator(String numbers) {
		this(Arrays.stream(numbers.split(","))
			.map(String::trim)
			.map(Integer::parseInt)
			.collect(Collectors.toList()));
	}

	@Override
	public Lotto issueLotto() {
		return new Lotto(numbers);
	}
}