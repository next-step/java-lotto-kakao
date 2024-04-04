package com.lotto.util;

import com.lotto.model.LottoNumber;

import java.util.List;

public interface LottoGenerateStrategy {
    List<LottoNumber> generate();
}
