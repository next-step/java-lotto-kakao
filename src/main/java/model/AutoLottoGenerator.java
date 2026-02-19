package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AutoLottoGenerator {
	private static final int MIN_LOTTO_NUMBER = 1;
	private static final int MAX_LOTTO_NUMBER = 45;
	private static final int LOTTO_NUMBER_COUNT = 6;

	private final List<Integer> numbers;

	public AutoLottoGenerator(){
		numbers = IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
			.boxed()
			.collect(Collectors.toCollection(ArrayList::new));
	}


	public Lotto issueLotto() {
		Collections.shuffle(numbers);
		return new Lotto(numbers.subList(0, LOTTO_NUMBER_COUNT).toArray(new Integer[0]));
	}
}
