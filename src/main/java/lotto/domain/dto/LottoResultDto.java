package lotto.domain.dto;

import lotto.domain.Prize;

import java.util.Map;

public class LottoResultDto {
    private final Map<Prize, Long> lottoResult;
    private final double resultRate;

    public LottoResultDto(Map<Prize, Long> lottoResult, double resultRate) {
        this.lottoResult = lottoResult;
        this.resultRate = resultRate;
    }

    public Map<Prize, Long> getLottoResult() {
        return lottoResult;
    }

    public double getResultRate() {
        return resultRate;
    }
}
