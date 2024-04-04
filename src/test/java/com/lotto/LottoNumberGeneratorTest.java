package com.lotto;

import com.lotto.model.LottoNumber;
import com.lotto.util.LottoNumberGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoNumberGeneratorTest {

    @Test
    void 각_로또_티켓은_6개의_숫자를_가지고_있어야_한다_성공() {
        LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();
        List<LottoNumber> lottoNumbers = lottoNumberGenerator.generate();

        assertThat(lottoNumbers).hasSize(6);
    }
}
