package lotto.model.ticket;

import java.util.List;
import java.util.Objects;

public record TicketManualGeneratorCommand(List<List<Integer>> numbers) implements TicketGeneratorCommand {
    public TicketManualGeneratorCommand{
        Objects.requireNonNull(numbers);
    }

    @Override
    public int count(){
        return numbers.size();
    }
}
