package domains;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public record LottoTickets(List<Lotto> lottos) {
    public LottoTickets {
        lottos = Collections.unmodifiableList(lottos);
    }

    public List<Rank> match(WinningLotto winningLotto) {
        return lottos.stream()
                .map(winningLotto::match)
                .collect(Collectors.toList());
    }
}