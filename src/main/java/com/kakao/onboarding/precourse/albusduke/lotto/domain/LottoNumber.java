package com.kakao.onboarding.precourse.albusduke.lotto.domain;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumber implements Comparable<LottoNumber> {

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final String NUMBER_OUT_OF_RANGE_ERR_MSG = "1 미만 또는 45 초과 숫자는 입력할 수 없습니다.";

    private static final Map<Integer, LottoNumber> CACHE = IntStream.rangeClosed(MIN_NUMBER, MAX_NUMBER)
            .boxed()
            .collect(Collectors.collectingAndThen(
                    Collectors.toMap(i -> i, LottoNumber::new),
                    Collections::unmodifiableMap
            ));

    private final int number;

    private LottoNumber(int number) {
        this.number = number;
    }

    /**
     * 로또 번호(1~45)에 해당하는 캐싱된 인스턴스를 반환한다.
     * 동일한 숫자는 항상 같은 인스턴스를 반환하여 인스턴스 수를 45개로 제한한다.
     */
    public static LottoNumber of(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException(NUMBER_OUT_OF_RANGE_ERR_MSG);
        }
        return CACHE.get(number);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(number);
    }

    public int getNumber() {
        return number;
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(this.number, o.number);
    }
}
