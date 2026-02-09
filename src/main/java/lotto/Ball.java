package lotto;

import java.util.Objects;

public class Ball {
    private int value;

    public Ball(int value) {
        if(value < 1 || value > 45)
            throw new IllegalArgumentException("1부터 45 사이의 숫자만 입력 가능합니다.");
        this.value = value;
    }
    public Ball(String value) {
        int num;
        try {
            num = Integer.parseInt(value);
        } catch (NumberFormatException e)
        {
            throw new IllegalArgumentException("로또 번호는 숫자만 입력 가능합니다.");
        }
        new Ball(num);
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
