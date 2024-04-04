package lotto.dto;

import lotto.domain.Prize;

import java.util.Map;

public class LottoResultDto {
    private final Map<String, Long> lottoResult;
    private final double resultRate;

    public LottoResultDto(Map<String, Long> lottoResult, double resultRate) {
        this.lottoResult = lottoResult;
        this.resultRate = resultRate;
    }

    public Map<String, Long> getLottoResult() {
        return lottoResult;
    }

    public double getResultRate() {
        return resultRate;
    }
}
