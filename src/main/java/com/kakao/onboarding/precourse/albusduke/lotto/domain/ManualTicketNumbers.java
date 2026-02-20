package com.kakao.onboarding.precourse.albusduke.lotto.domain;

import java.util.List;

public class ManualTicketNumbers {
    private final List<Integer> numbers;

    public ManualTicketNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
