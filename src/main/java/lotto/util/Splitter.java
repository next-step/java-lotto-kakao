package lotto.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Splitter {

    public static ArrayList<Integer> splitNumbers(String input) {
        return Arrays.stream(input.split(", "))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
