package straddcal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class SeparatorTest {

    @Test
    @DisplayName("커스텀 구분자가 없을 떄")
    public void emptyCustom(){
        Separator sep = new Separator("1:2,3");
        assertThat(sep.split().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("커스텀 구분자가 있을 때")
    public void hasCustom(){
        Separator sep = new Separator("//a\\n1:2,3a5");
        assertThat(sep.split().size()).isEqualTo(4);
    }

    @Test
    @DisplayName("잘못된 구분자가 있을 때")
    public void errorSep(){
        Separator sep = new Separator("1:2,3a5");
        assertThatThrownBy(()->sep.split()).isInstanceOf(RuntimeException.class);
    }

}