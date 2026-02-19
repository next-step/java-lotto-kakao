package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoNumbersTest {

    @Test
    @DisplayName("로또 번호 6개를 생성하고 오름차순 정렬한다.")
    public void generateAndSortTest() {
        LottoNumbers lottoNumbers = new LottoNumbers(List.of(45, 3, 12, 27, 8, 34));

        List<Integer> numbers = lottoNumbers.getLottoNumberList().stream()
                .map(LottoNumber::getNumber)
                .toList();

        assertThat(numbers).hasSize(6);
        assertThat(numbers).isSorted();
        assertThat(numbers.stream().distinct().count()).isEqualTo(6);
        assertThat(numbers).allMatch(number -> number >= 1 && number <= 45);
    }

    @Test
    @DisplayName("생성자로 넣은 숫자 목록이 정렬된다.")
    public void sortLottoNumberListTest() {
        LottoNumbers lottoNumbers = new LottoNumbers(List.of(9, 1, 5, 3, 7, 2));

        assertThat(lottoNumbers.getLottoNumberList().stream().map(LottoNumber::getNumber).toList())
                .containsExactly(1, 2, 3, 5, 7, 9);
    }
}
