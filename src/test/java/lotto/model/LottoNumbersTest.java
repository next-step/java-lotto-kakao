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

    @Test
    @DisplayName("로또 번호는 보너스 번호를 포함하여 생성할 수 있다.")
    public void test_lotto_with_bonus() {
        LottoNumbers numbers = new LottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6), 7);

        assertEquals(7, numbers.getBonus());
    }

    @Test
    @DisplayName("로또 번호는 보너스 번호가 없어도 생성할 수 있다.")
    public void test_lotto_without_bonus() {
        LottoNumbers numbers = new LottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6));

        assertEquals(0, numbers.getBonus());
    }

    @Test
    @DisplayName("보너스 번호의 범위는 1부터 45까지 허용된다.")
    public void test_bonus_range() {
        assertDoesNotThrow(() ->
            new LottoNumbers(Arrays.asList(2, 3, 4, 5, 6, 7), 1)
        );
        assertDoesNotThrow(() ->
            new LottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6), 45)
        );
        assertThrows(IllegalArgumentException.class, () ->
            new LottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6), 0)
        );
        assertThrows(IllegalArgumentException.class, () ->
            new LottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6), 46)
        );
    }

    @Test
    @DisplayName("당첨 번호와 6개가 일치하면 1등으로 판정한다.")
    public void test_compare_rank_first() {
        LottoNumbers user = new LottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoNumbers winning = new LottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6), 7);
        LottoResult result = winning.compare(user);

        assertEquals(LottoResult.RANK_FIRST, result);
    }

    @Test
    @DisplayName("당첨 번호와 5개가 일치하고, 나머지 1개가 보너스 번호와 일치하면 2등으로 판정한다.")
    public void test_compare_rank_second() {
        LottoNumbers user = new LottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 7));
        LottoNumbers winning = new LottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6), 7);
        LottoResult result = winning.compare(user);

        assertEquals(LottoResult.RANK_SECOND, result);
    }

    @Test
    @DisplayName("당첨 번호와 5개가 일치하면 3등으로 판정한다.")
    public void test_compare_rank_third() {
        LottoNumbers user = new LottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 8));
        LottoNumbers winning = new LottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6), 7);
        LottoResult result = winning.compare(user);

        assertEquals(LottoResult.RANK_THIRD, result);
    }

    @Test
    @DisplayName("당첨 번호와 4개가 일치하면 4등으로 판정한다.")
    public void test_compare_rank_fourth() {
        LottoNumbers user = new LottoNumbers(Arrays.asList(1, 2, 3, 4, 8, 9));
        LottoNumbers winning = new LottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6), 7);
        LottoResult result = winning.compare(user);

        assertEquals(LottoResult.RANK_FOURTH, result);
    }

    @Test
    @DisplayName("당첨 번호와 3개가 일치하면 5등으로 판정한다.")
    public void test_compare_rank_fifth() {
        LottoNumbers user = new LottoNumbers(Arrays.asList(1, 2, 3, 8, 9, 10));
        LottoNumbers winning = new LottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6), 7);
        LottoResult result = winning.compare(user);

        assertEquals(LottoResult.RANK_FIFTH, result);
    }

    @Test
    @DisplayName("당첨 번호와 2개가 일치하면 당첨되지 않음으로 판정한다.")
    public void test_compare_rank_none() {
        LottoNumbers user = new LottoNumbers(Arrays.asList(1, 2, 3, 9, 10, 11));
        LottoNumbers winning = new LottoNumbers(Arrays.asList(1, 2, 4, 5, 6, 7), 3);
        LottoResult result = winning.compare(user);

        assertEquals(LottoResult.RANK_NONE, result);
    }
}
