package lotto;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
	private List<Lotto> lottoList;

	public Lottos() {
		this.lottoList = new ArrayList<>();
	}

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

	public int getLottoCount() {
		return this.lottoList.size();
	}

	public void add(Lotto lotto) {
		this.lottoList.add(lotto);
	}

	public void addRandomLotto(long lottoCount) {
		for (int i = 0; i < lottoCount; i++) {
			this.lottoList.add(new Lotto());
		}
	}
}