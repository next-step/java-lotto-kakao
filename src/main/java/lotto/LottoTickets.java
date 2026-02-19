package lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class LottoTickets {
	private final ArrayList<LottoTicket> lottoTickets;

	public LottoTickets(List<LottoTicket> lottoTickets) {
		if (lottoTickets == null) {
			this.lottoTickets = new ArrayList<>();
			return;
		}
		this.lottoTickets = new ArrayList<>(lottoTickets);
	}

	public int size() {
		return lottoTickets.size();
	}

	public void forEach(Consumer<LottoTicket> action) {
		for (LottoTicket lottoTicket : lottoTickets) {
			action.accept(lottoTicket);
		}
	}

	public LottoStatistics buildStatistics(LottoAnswer lottoAnswer) {
		LottoStatistics lottoStatistics = new LottoStatistics();
		for (LottoTicket lottoTicket : lottoTickets) {
			lottoStatistics.add(lottoAnswer.judge(lottoTicket));
		}
		return lottoStatistics;
	}

	public void merge(LottoTickets other){
		if (other == null)
			return;

		for (LottoTicket lottoTicket : other.lottoTickets) {
			lottoTickets.add(copyTicket(lottoTicket));
		}
	}

	private LottoTicket copyTicket(LottoTicket lottoTicket) {
		return new LottoTicket(lottoTicket.getNumbers());
	}
}
