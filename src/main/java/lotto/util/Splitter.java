package lotto.util;

import lotto.exception.InputErrorCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Splitter {

    public static ArrayList<Integer> splitNumbers(String input) {
        validateNotEmptyInput(input);

        try {
            return Arrays.stream(input.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException(InputErrorCode.INVALID_NUMBER_FORMAT.getMessage());
        }
    }

    private static void validateNotEmptyInput(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(InputErrorCode.EMPTY_INPUT.getMessage());
        }
    }
}
