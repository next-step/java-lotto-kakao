package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LottoTicketRandomGenerator extends LottoTicketGenerator{

	private final Random random;

	public LottoTicketRandomGenerator(Random random) {
		this.random = random;
	}

	public LottoTicketRandomGenerator() {
		this.random = new Random();
	}

	public LottoTicket generate() {
		List<Integer> allNumbers = new ArrayList<>(IntStream.rangeClosed(1, 45)
				.boxed()
				.toList());
		
		Collections.shuffle(allNumbers, random);
		List<Integer> numbers = allNumbers.subList(0, LottoTicket.LOTTO_LENGTH);

		return super.generate(numbers);
	}

	public List<LottoTicket> generate(int count) {
		return Stream.generate(this::generate)
				.limit(count)
				.collect(Collectors.toList());
	}
}
