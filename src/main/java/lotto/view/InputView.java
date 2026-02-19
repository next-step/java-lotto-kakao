package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.util.Splitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class InputView {

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public int readPurchaseAmount() throws IOException {
        return Integer.parseInt(br.readLine());
    }

    public Set<LottoNumber> readLottoNumbersList() throws IOException {
        return Splitter.splitNumbers(br.readLine()).stream()
                .map(LottoNumber::new)
                .collect(Collectors.toSet());
    }

    public int readBonusNumber() throws IOException {
        return Integer.parseInt(br.readLine());
    }

    public List<Lotto> readManualLottos(int manualCount) throws IOException {
        List<Lotto> result = new ArrayList<>();
        for (int i = 0; i < manualCount; i++) {
            Set<LottoNumber> numbers = readLottoNumbersList();
            result.add(new Lotto(numbers));
        }
        return result;
    }
}
