package lotto.domain.dto;

import lotto.domain.Prize;

import java.util.Map;

public class LottoResultDto {
    private final Map<Prize, Integer> lottoResult;
    private final double resultRate;

    public LottoResultDto(Map<Prize, Integer> lottoResult, double resultRate) {
        this.lottoResult = lottoResult;
        this.resultRate = resultRate;
    }

    public Map<Prize, Integer> getLottoResult() {
        return lottoResult;
    }

    public double getResultRate() {
        return resultRate;
    }
}
