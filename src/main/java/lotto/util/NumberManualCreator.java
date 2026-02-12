package lotto.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NumberManualCreator implements NumberCreator {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public Set<Integer> numberCreate() {
        try {
            ArrayList<Integer> arr = Splitter.splitNumbers(br.readLine());
            return new HashSet<>(arr);
        }
        catch (IOException e) {
            return new HashSet<>(List.of(1, 2, 3, 4, 5, 6));
        }
    }
}
