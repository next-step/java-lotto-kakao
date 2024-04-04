package com.lotto.model;

import com.lotto.util.LottoGenerateStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTickets {

    private final List<LottoNumbers> lottoTickets = new ArrayList<>();

    public LottoTickets(int lottoTicketSize, LottoGenerateStrategy lottoGenerateStrategy) {
        for (int i = 0; i < lottoTicketSize; i++) {
            List<LottoNumber> lottoNumbers = lottoGenerateStrategy.generate();
            lottoTickets.add(new LottoNumbers(lottoNumbers));
        }
    }

    public List<LottoNumbers> getLottoTickets() {
        return lottoTickets;
    }

    public int size() {
        return lottoTickets.size();
    }

    public List<LottoRank> matchAll(TargetLotto targetLotto) {
        return lottoTickets.stream()
                .map(targetLotto::match)
                .collect(Collectors.toList());
    }
}
