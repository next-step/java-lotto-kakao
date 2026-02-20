package com.kakao.onboarding.precourse.albusduke.lotto.service;

import com.kakao.onboarding.precourse.albusduke.lotto.domain.PurchaseAmount;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.TicketQuantity;

import java.util.List;
import java.util.stream.IntStream;

import com.kakao.onboarding.precourse.albusduke.lotto.domain.LottoGames;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.LottoNumbers;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.LottoNumbersGenerator;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.ManualTicketNumbers;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.ManualTicketQuantity;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.Money;

public class LottoService {

    private static final int LOTTO_COST = 1_000;

    private final LottoNumbersGenerator lottoNumbersGenerator;

    public LottoService(LottoNumbersGenerator lottoNumbersGenerator) {
        this.lottoNumbersGenerator =  lottoNumbersGenerator;
    }

    public Money createMoneyBy(PurchaseAmount purchaseAmount) {
        return new Money(purchaseAmount.purchaseAmount());
    }

    public TicketQuantity calculateTicketQuantity(Money money, ManualTicketQuantity manualTicketQuantity) {
        Money lottoCost = new Money(LOTTO_COST);
        int totalQuantity = money.divide(lottoCost);
        int manualQuantity = manualTicketQuantity.getQuantity();
        int randomQuantity = totalQuantity - manualQuantity;
        if (randomQuantity < 0) {
            throw new IllegalArgumentException("자동 티켓 수는 0 이상이어야 합니다.");
        }
        return new TicketQuantity(randomQuantity, manualQuantity);
    }

    public List<LottoNumbers> purchaseRandomLottoGames(TicketQuantity ticketQuantity) {
        List<LottoNumbers> lottoNumbers = IntStream.range(0, ticketQuantity.getRandomQuantity())
                .mapToObj(i -> lottoNumbersGenerator.generate())
                .toList();
        return lottoNumbers;
    }

    public LottoNumbers purchaseManualLottoGame(ManualTicketNumbers manualTicketNumbers) {
        LottoNumbers lottoNumbers = new LottoNumbers(manualTicketNumbers.getNumbers());
        return lottoNumbers;
    }
}
