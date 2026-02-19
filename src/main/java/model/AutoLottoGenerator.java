package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import Interface.LottoGenerator;

public class AutoLottoGenerator implements LottoGenerator {
	private final List<Integer> numbers;

	public AutoLottoGenerator(){
		numbers = IntStream.rangeClosed(LottoNumber.MIN_NUMBER, LottoNumber.MAX_NUMBER)
			.boxed()
			.collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public Lotto issueLotto() {
		Collections.shuffle(numbers);
		return new Lotto(new ArrayList<>(numbers.subList(0, LottoNumber.LOTTO_SIZE)));
	}
}
