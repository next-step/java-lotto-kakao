package lotto.util;

import lotto.domain.LottoBalls;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class LottoManualCreator implements LottoNumberCreator {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public LottoBalls lottoCreate() {
        try {
            ArrayList<Integer> arr = Splitter.splitNumbers(br.readLine());
            return new LottoBalls(new HashSet<>(arr));
        }
        catch (IOException e) {
            return new LottoBalls(new HashSet<>(List.of(1, 2, 3, 4, 5, 6)));
        }
    }
}
