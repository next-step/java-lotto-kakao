package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ticket {

    private final List<Integer> numbers;
    private final long key;

    public Ticket(Integer... numbers) {
        this(new ArrayList<>(Arrays.asList(numbers)));
    }

    public Ticket(List<Integer> numbers) {
        numbers.sort(((o1, o2) -> o1 - o2));
        long key = 0L;
        for (int number : numbers) {
            key |= (1L << number);
        }
        this.numbers = numbers;
        this.key = key;
    }

    public long getKey() {
        return key;
    }

    public WinLevel getWinLevel(GameScore gameScore) {
        int winMatchcount = 0;
        for (var winNumber : gameScore.getWinNumbers()) {
            winMatchcount += (key & (1L << winNumber)) == 0 ? 0 : 1;
        }
        boolean bonusMatched = (key & (1L << gameScore.getBonusNumber())) > 0;
        return WinLevel.make(winMatchcount, bonusMatched);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (Integer number : numbers) {
            stringBuilder.append(number).append(", ");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
