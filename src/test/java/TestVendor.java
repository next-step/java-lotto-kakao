import static org.assertj.core.api.AssertionsForClassTypes.*;

import Interface.LottoGenerator;
import model.Vendor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class TestVendor {
    private LottoGenerator mockGenerator;
    private Vendor vendor;

    @BeforeEach
    void setUp() {
        mockGenerator = mock(LottoGenerator.class);
        vendor = new Vendor(mockGenerator);
    }

    @Test
    void 올바른_금액이_주어졌을때는_적정_개수의_로또를_반환한다() {
        Integer money = 12000;
        vendor.sell(money);
        verify(mockGenerator, times(12)).issueLotto();
    }

    @Test
    void 천원_단위의_금액이_들어오지_않았을_때_에러를_반환한다() {
        Integer money = 13700;

        try {
            vendor.sell(money);
            fail();
        }
        catch (RuntimeException e) {
            assertThat(e.getMessage()).isEqualTo("천원 단위의 금액을 입력해주세요.");
        }
    }

    @Test
    void 양이_아닌_정수가_들어오면_에러를_반환한다(){
        Integer money = 0;

        try{
            vendor.sell(money);
            fail();
        }
        catch (RuntimeException e){
            assertThat(e.getMessage()).isEqualTo("금액은 0보다 커야 합니다");
        }
    }
}
