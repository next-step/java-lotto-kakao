package lotto.util;

import java.util.HashSet;
import java.util.Set;

public class NumberManualCreator implements NumberCreator {

    private final String manualNumberString;

    public NumberManualCreator(String manualNumberString) {
        this.manualNumberString = manualNumberString;
    }

    @Override
    public Set<Integer> numberCreate() {
        return new HashSet<>(Splitter.splitNumbers(this.manualNumberString));
    }
}
