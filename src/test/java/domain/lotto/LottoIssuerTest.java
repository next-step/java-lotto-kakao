package domain.lotto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LottoIssuerTest {

    @Test
    void getCount() {
        LottoFactory lottoFactory = new LottoFactory();
        LottoIssuer lottoIssuer = new LottoIssuer(lottoFactory);

        assertEquals(1, lottoIssuer.getCount(1000));
    }

    @Test
    void issueAuto() {
        LottoFactory lottoFactory = new LottoFactory();
        LottoIssuer lottoIssuer = new LottoIssuer(lottoFactory);

        LottoGroup lottoGroup = lottoIssuer.issueAuto(1000);
        assertEquals(1, lottoGroup.getSize());
    }
}