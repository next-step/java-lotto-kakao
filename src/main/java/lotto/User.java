package lotto;

import java.util.ArrayList;
import java.util.List;

public class User {
    private long price;
    private List<Lotto> lottos;
    private long award;

    private static final long TICKET_COST = 1000;

    public User(long price) {
        if (price <= 0 || price % TICKET_COST != 0) {
            throw new IllegalArgumentException("잘못된 구입 금액입니다.");
        }
        this.price = price;
        lottos = new ArrayList<>();
        for (int i = 0; i < price / TICKET_COST; i++) {
            lottos.add(new Lotto());
        }
    }

    public List<Lotto> getLottos() {return this.lottos;}
}
