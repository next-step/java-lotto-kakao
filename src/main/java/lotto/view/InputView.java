package lotto.view;

import lotto.util.Splitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class InputView {

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public int readTotalPurchaseAmount() throws IOException {
        return Integer.parseInt(br.readLine());
    }

    public int readManualCount() throws IOException {
        return Integer.parseInt(br.readLine());
    }

    public List<String> readManualLottoNumbers(int count) throws IOException {
        List<String> manualNumbers = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            manualNumbers.add(br.readLine());
        }
        return manualNumbers;
    }

    public Set<Integer> readWinningNumbers() throws IOException {
        return new HashSet<>(Splitter.splitNumbers(br.readLine()));
    }

    public int readBonusNumber() throws IOException {
        return Integer.parseInt(br.readLine());
    }
}
