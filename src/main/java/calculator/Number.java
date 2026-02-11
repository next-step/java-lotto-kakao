package calculator;

import java.util.ArrayList;
import java.util.List;

public class Number {

    private final String delimiter = " ";
    private List<Integer> numbers;

    // 1 2 3 형식
    public Number(String input) {
        String[] result = validate(input);
        numbers = new ArrayList<>();
        createNumbers(result);

    }

    public String[] validate(String input) {
        String[] split = input.split(delimiter);
        for (String s : split) {
            validateNumber(s);
            validateRange(Integer.parseInt(s));
        }
        return split;
    }

    public void validateNumber(String number) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자 형식이 아닙니다.", e);
        }
    }

    public void validateRange(int value) {
        if (value < 0) {
            throw new RuntimeException("음수가 입력되었습니다.");
        }
    }

    public void createNumbers(String[] input) {
        for (String s : input) {
            numbers.add(Integer.parseInt(s));
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public Integer calculate() {
        return this.numbers.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
