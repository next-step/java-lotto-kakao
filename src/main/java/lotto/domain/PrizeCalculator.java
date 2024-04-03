package lotto.domain;


import java.util.Map;

public class PrizeCalculator {

    private final static double TICKET_PRICE = 1000.0d;

    public static double calculate(Map<Prize, Integer> result) {
         Long total = result.entrySet().stream()
             .map((entry) -> entry.getKey().getReward() * entry.getValue())
             .reduce(0L, Long::sum);

        int count = result.values().stream().reduce(0, Integer::sum);

        return total / (count * TICKET_PRICE);
    }
}
