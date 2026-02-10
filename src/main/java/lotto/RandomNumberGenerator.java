package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomNumberGenerator implements RandomPickStrategy {
    @Override
    public List<Integer> generate() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 45; i++) numbers.add(i);

        Collections.shuffle(numbers);
        List<Integer> result = numbers.subList(0, 6);
        Collections.sort(result);

        return result;
    }
}
