package lotto.model;

import java.util.Objects;
import java.util.Random;

public record TicketRandomGeneratorCommand(int count, Random random) implements TicketGeneratorCommand{
    public TicketRandomGeneratorCommand{
        if(count <= 0) throw new IllegalArgumentException("발행하려는 티켓 수는 1 이상이어야합니다.");
        Objects.requireNonNull(random);
    }
    public TicketRandomGeneratorCommand(int count) {
        this(count, new Random());
    }
}
