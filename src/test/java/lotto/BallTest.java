package lotto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BallTest {

    @Test
    void compareBall() {
        Ball ball1 = new Ball(1);
        Ball ball2 = new Ball(1);
        assertThat(ball1.equals(ball2)).isTrue();
    }
}
