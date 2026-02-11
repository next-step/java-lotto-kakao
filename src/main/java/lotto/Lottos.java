package lotto;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
	private List<Lotto> lottoList;

	public Lottos(long count) {
		List<Lotto> tempLottos = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			tempLottos.add(new Lotto());
		}
		this.lottoList = tempLottos;
	}

	public List<Lotto> getLottoList() {
		return lottoList;
	}

	public void setLottoList(List<Lotto> lottos) {
		this.lottoList = lottos;
	}
}