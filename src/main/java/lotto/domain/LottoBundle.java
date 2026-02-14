package lotto.domain;

import java.util.List;

public class LottoBundle {
	private final List<Lotto> lottos;

	public LottoBundle(List<Lotto> lottos) {
		this.lottos = lottos;
	}

	public int size() {
		return lottos.size();
	}

	public List<Lotto> getLottos() {
		return lottos;
	}

	public List<Rank> match(WinningLotto winningLotto) {
		return lottos.stream()
			.map(winningLotto::judge)
			.toList();
	}
}
