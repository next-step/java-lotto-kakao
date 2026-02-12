package lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoList {
	private List<Lotto> lottos;

	public LottoList() {
		lottos = new ArrayList<>();
	}

	public void addLotto(Lotto lotto) {
		lottos.add(lotto);
	}

	public List<Lotto> getLottos() {
		return lottos;
	}

	public void setLottos(List<Lotto> lottos) {
		this.lottos = lottos;
	}
}
