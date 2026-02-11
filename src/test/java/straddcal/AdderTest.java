package straddcal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AdderTest {

    @Test
    @DisplayName("멀쩡한 입력")
    public void success(){
        List<NumberObject> numbers = List.of(
                new NumberObject(1),
                new NumberObject(2),
                new NumberObject(3)
        );

        Adder adder = new Adder(numbers);
        assertThat(adder.sum().toString()).isEqualTo("6");
    }

}