package com.kakao.onboarding.precourse.albusduke.lotto.domain;

public class TicketQuantity {
    private final int randomQuantity;
    private final int manualQuantity;

    public TicketQuantity(int randomQuantity, int manualQuantity) {
        this.randomQuantity = randomQuantity;
        this.manualQuantity = manualQuantity;
    }

    public int getRandomQuantity() {
        return randomQuantity;
    }

    public int getManualQuantity() {
        return manualQuantity;
    }
}
