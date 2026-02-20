package com.kakao.onboarding.precourse.albusduke.lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class LottoNumberTest {
    @Test
    void 숫자_범위_초과인_숫자로_로또_번호를_생성할_수_없다() {
        assertThatThrownBy(() ->
                LottoNumber.of(46)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 숫자_범위_미만인_숫자로_로또_번호를_생성할_수_없다() {
        assertThatThrownBy(() ->
                LottoNumber.of(0)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 범위_이내인_숫자로_로또_번호를_생성할_수_있다() {
        assertThatCode(() ->
                LottoNumber.of(1)
        ).doesNotThrowAnyException();
    }

    @Test
    void 최소값_1로_로또_번호를_생성할_수_있다() {
        assertThatCode(() ->
                LottoNumber.of(1)
        ).doesNotThrowAnyException();
    }

    @Test
    void 최대값_45로_로또_번호를_생성할_수_있다() {
        assertThatCode(() ->
                LottoNumber.of(45)
        ).doesNotThrowAnyException();
    }

    @Test
    void 같은_숫자로_생성된_로또_번호는_같다() {
        LottoNumber number1 = LottoNumber.of(10);
        LottoNumber number2 = LottoNumber.of(10);

        assertThat(number1).isEqualTo(number2);
        assertThat(number1.hashCode()).isEqualTo(number2.hashCode());
    }

    @Test
    void 같은_숫자는_캐시된_동일_인스턴스를_반환한다() {
        assertThat(LottoNumber.of(10)).isSameAs(LottoNumber.of(10));
    }

    @Test
    void 다른_숫자로_생성된_로또_번호는_다르다() {
        LottoNumber number1 = LottoNumber.of(10);
        LottoNumber number2 = LottoNumber.of(20);

        assertThat(number1).isNotEqualTo(number2);
    }

    @Test
    void 로또_번호를_비교할_수_있다() {
        LottoNumber number1 = LottoNumber.of(10);
        LottoNumber number2 = LottoNumber.of(20);
        LottoNumber number3 = LottoNumber.of(10);

        assertThat(number1.compareTo(number2)).isLessThan(0);
        assertThat(number2.compareTo(number1)).isGreaterThan(0);
        assertThat(number1.compareTo(number3)).isEqualTo(0);
    }

    @Test
    void 로또_번호를_가져올_수_있다() {
        LottoNumber number = LottoNumber.of(10);

        assertThat(number.getNumber()).isEqualTo(10);
    }
}
