package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LottoTicketRandomGenerator implements LottoTicketGenerator<TicketRandomGeneratorCommand>{
	public Class<TicketRandomGeneratorCommand> commandType(){
		return TicketRandomGeneratorCommand.class;
	}

	private LottoTicket generateTicket(Random random) {
		List<Integer> allNumbers = new ArrayList<>(IntStream.rangeClosed(1, 45).boxed().toList());

		Collections.shuffle(allNumbers, random);
		List<Integer> numbers = allNumbers.subList(0, LottoTicket.LOTTO_LENGTH);
		return new LottoTicket(numbers.stream().map(LottoNumber::of).toList());
	}

	public List<LottoTicket> generate(TicketRandomGeneratorCommand command) {
		return Stream.generate(()-> this.generateTicket(command.random()))
				.limit(command.count())
				.collect(Collectors.toList());
	}
}
