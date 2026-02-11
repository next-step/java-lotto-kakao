package calculator;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        CalculatorView view = new CalculatorView();
        Calculator calculator = new Calculator();
        Parser parser = new Parser();

        String input = view.readLine();

        List<Number> numbers = getNumbers(parser, input);
        int result  = calculator.sum(numbers);
        view.printLine(result);
    }

    private static List<Number> getNumbers(Parser parser, String input) {
        String[] list = parser.split(input);
        List<Number> numbers = new ArrayList<>();

        for (String s : list) {
            numbers.add(new Number(s));
        }
        return numbers;
    }
}
