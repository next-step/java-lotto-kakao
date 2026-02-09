package calculator;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        CalculatorView view = new CalculatorView();
        Calculator calculator = new Calculator();
        Parser parser = new Parser();

        String input = view.readLine();

        String[] list = parser.split(input);
        List<Number> numbers = new ArrayList<>();

        for(int i = 0; i < list.length; i++){
            numbers.add(new Number(list[i]));
        }
        int result  = calculator.sum(numbers);

        view.printLine(result);
    }
}
