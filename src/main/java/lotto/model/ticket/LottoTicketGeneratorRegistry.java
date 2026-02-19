package lotto.model.ticket;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LottoTicketGeneratorRegistry {
    private final Map<Class<? extends TicketGeneratorCommand>, LottoTicketGenerator<?>> generators;

    public LottoTicketGeneratorRegistry(List<LottoTicketGenerator<?>> generators) {
        this.generators = generators.stream()
                .collect(Collectors.toMap(LottoTicketGenerator::commandType, Function.identity()));
    }

    @SuppressWarnings("unchecked")
    public <C extends TicketGeneratorCommand> LottoTicketGenerator<C> find(C command) {
        LottoTicketGenerator<?> generator = generators.get(command.getClass());
        if (generator == null) {
            throw new IllegalArgumentException("지원하지 않는 command: " + command.getClass().getSimpleName());
        }
        return (LottoTicketGenerator<C>) generator;
    }

}
