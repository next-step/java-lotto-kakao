package lotto.dto;

import java.util.List;

public class TicketDto {
    List<Integer> numbers;

    public TicketDto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
