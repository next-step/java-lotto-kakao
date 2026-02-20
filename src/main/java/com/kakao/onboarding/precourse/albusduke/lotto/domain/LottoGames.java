package com.kakao.onboarding.precourse.albusduke.lotto.domain;

import java.util.List;

public record LottoGames(List<LottoNumbers> games) {

    public List<LottoNumbers> getGames() {
        return games();
    }
}
