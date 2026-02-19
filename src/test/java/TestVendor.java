import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;

import model.Money;
import model.Vendor;
import org.junit.jupiter.api.Test;

public class TestVendor {
    @Test
    void 올바른_금액이_주어졌을때는_적정_개수의_로또를_반환한다(){
        Integer money = 12000;

        Vendor vendor = new Vendor();

        assertThat(vendor.sell(money).getQuantity()).isEqualTo(12);

    }

    @Test
    void 천원_단위의_금액이_들어오지_않았을_때_에러를_반환한다() {
        Integer money = 13700;
        Vendor vendor = new Vendor();
        assertThatThrownBy(() -> vendor.sell(money))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("천원 단위의 금액을 입력해주세요.");
    }

    @Test
    void 양이_아닌_정수가_들어오면_에러를_반환한다(){
        Integer money = 0;
        Vendor vendor = new Vendor();
        assertThatThrownBy(() -> vendor.sell(money))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("금액은 0보다 커야 합니다");
    }

    @Test
    void 수동과_자동_구매를_합쳐_총_구매_개수를_반환한다() {
        Vendor vendor = new Vendor();
        List<String> manualLottoInputs = List.of(
                "8, 21, 23, 41, 42, 43",
                "3, 5, 11, 16, 32, 38",
                "7, 11, 16, 35, 36, 44"
        );

        assertThat(vendor.sell(new Money(14000), 3, manualLottoInputs).getQuantity()).isEqualTo(14);
    }

    @Test
    void 수동_구매_수가_전체_구매_수보다_크면_예외를_반환한다() {
        Vendor vendor = new Vendor();
        List<String> manualLottoInputs = List.of(
                "1, 2, 3, 4, 5, 6",
                "7, 8, 9, 10, 11, 12",
                "13, 14, 15, 16, 17, 18"
        );

        assertThatThrownBy(() -> vendor.sell(new Money(2000), 3, manualLottoInputs))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수동 로또 수는 전체 구매 수를 초과할 수 없습니다.");
    }

    @Test
    void 수동_입력_개수가_수동_구매_수와_다르면_예외를_반환한다() {
        Vendor vendor = new Vendor();
        List<String> manualLottoInputs = List.of(
                "1, 2, 3, 4, 5, 6",
                "7, 8, 9, 10, 11, 12"
        );

        assertThatThrownBy(() -> vendor.sell(new Money(4000), 3, manualLottoInputs))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수동 로또 번호 입력 개수가 수동 구매 수와 다릅니다.");
    }

    @Test
    void 수동_번호가_6개가_아니면_예외를_반환한다() {
        Vendor vendor = new Vendor();

        assertThatThrownBy(() -> vendor.sell(new Money(3000), 1, List.of("1, 2, 3, 4, 5")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수동 로또 번호는 6개의 숫자여야 합니다.");
    }

    @Test
    void 수동_번호가_숫자가_아니면_예외를_반환한다() {
        Vendor vendor = new Vendor();

        assertThatThrownBy(() -> vendor.sell(new Money(3000), 1, List.of("1, 2, a, 4, 5, 6")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수동 로또 번호는 숫자여야 합니다.");
    }

    @Test
    void 수동_번호가_범위를_벗어나면_예외를_반환한다() {
        Vendor vendor = new Vendor();

        assertThatThrownBy(() -> vendor.sell(new Money(3000), 1, List.of("1, 2, 3, 4, 5, 46")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수동 로또 번호는 1~45 사이의 숫자여야 합니다.");
    }

    @Test
    void 수동_번호가_중복되면_예외를_반환한다() {
        Vendor vendor = new Vendor();

        assertThatThrownBy(() -> vendor.sell(new Money(3000), 1, List.of("1, 2, 3, 4, 5, 5")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수동 로또 번호는 중복될 수 없습니다.");
    }

    @Test
    void 수동_번호가_비어있으면_예외를_반환한다() {
        Vendor vendor = new Vendor();

        assertThatThrownBy(() -> vendor.sell(new Money(3000), 1, List.of("   ")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수동 로또 번호는 비어 있을 수 없습니다.");
    }

    @Test
    void 수동_구매_수가_음수면_예외를_반환한다() {
        Vendor vendor = new Vendor();

        assertThatThrownBy(() -> vendor.sell(new Money(3000), -1, List.of()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수동 구매 수는 0 이상이어야 합니다.");
    }

}
