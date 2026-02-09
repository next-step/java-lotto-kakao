package lotto;

import java.util.Objects;

public class Ball {
    private int value;

    public Ball(int value) {
        this.value = value;
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
}
