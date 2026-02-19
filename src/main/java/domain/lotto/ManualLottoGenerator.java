package domain.lotto;

import java.util.List;

public class ManualLottoGenerator implements LottoGenerator {

    private final List<Integer> numbers;

    public ManualLottoGenerator(List<Integer> numbers) {
        this.numbers = List.copyOf(numbers);
    }

    @Override
    public List<Integer> generate() {
        return numbers;
    }
}
