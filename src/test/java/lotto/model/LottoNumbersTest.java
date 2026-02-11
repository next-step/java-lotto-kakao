package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LottoNumbersTest {

    @Test
    @DisplayName("하나의 로또는 6개의 숫자로 구성되어야 한다.")
    public void test_lotto_numbers() {
        assertDoesNotThrow(() ->
            new LottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6))
        );
        assertThrows(IllegalArgumentException.class, () ->
            new LottoNumbers(Arrays.asList(1, 2, 3, 4, 5))
        );
        assertThrows(IllegalArgumentException.class, () ->
            new LottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6, 7))
        );
    }

    @Test
    @DisplayName("로또 번호는 비어있을 수 없다.")
    public void test_lotto_numbers_empty() {
        assertThrows(IllegalArgumentException.class, () ->
            new LottoNumbers(new ArrayList<>())
        );
    }

    @Test
    @DisplayName("로또 번호를 구성하는 숫자는 1 미만일 수 없다.")
    public void test_lotto_range_lower() {
        assertThrows(IllegalArgumentException.class, () ->
            new LottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 0))
        );
    }

    @Test
    @DisplayName("로또 번호를 구성하는 숫자는 45 초과일 수 없다.")
    public void test_lotto_range_upper() {
        assertThrows(IllegalArgumentException.class, () ->
            new LottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 46))
        );
    }

    @Test
    @DisplayName("로또 번호를 구성하는 숫자는 서로 중복될 수 없다.")
    public void test_lotto_duplicated() {
        assertThrows(IllegalArgumentException.class, () ->
            new LottoNumbers(Arrays.asList(1, 1, 2, 3, 4, 5))
        );
    }
}
