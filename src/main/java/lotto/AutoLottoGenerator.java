package lotto;

import java.util.List;
import java.util.stream.IntStream;

public class AutoLottoGenerator implements LottoGenerator {

	private final int count;

	public AutoLottoGenerator(int count) {
		this.count = count;
	}

	@Override
	public LottoList generate() {
		List<Lotto> lottos = IntStream.range(0, count)
			.mapToObj(i -> new Lotto())
			.toList();
		return new LottoList(lottos);
	}
}
