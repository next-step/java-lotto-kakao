package lotto;

import java.util.Objects;

public class Ball implements Comparable<Ball>{
    private final int value;

    public Ball() {
        this.value = 0;
    }

    public Ball(int value) {
        validateRange(value);
        this.value = value;
    }

    public Ball(String value) {
        this(parseAndValidate(value));
    }

    private static int parseAndValidate(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("로또 번호는 숫자만 입력 가능합니다.");
        }
    }

    private void validateRange(int value) {
        if (value < 1 || value > 45) {
            throw new IllegalArgumentException("1부터 45 사이의 숫자만 입력 가능합니다.");
        }
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ball ball = (Ball) o;
        return value == ball.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public int compareTo(Ball o) {
        return this.value - o.getValue();
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
