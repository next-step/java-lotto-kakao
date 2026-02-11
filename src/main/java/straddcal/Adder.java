package straddcal;

import java.util.List;

public class Adder {

    private final List<NumberObject> numbers;

    public Adder(List<NumberObject> numbers) {
        this.numbers = numbers;
    }

    public NumberObject sum() {
        return numbers.stream().reduce((a,b)->{
            a.sum(b);
            return a;
        }).orElseGet(()->new NumberObject(0));
    }
}
