import model.LottoRank;
import org.junit.jupiter.api.Test;
import view.OutputView;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestOutputView {
    @Test
    void 수익률이_1보다_작거나_같으면_손해_문구를_출력한다() {
        OutputView outputView = new OutputView();
        Map<LottoRank, Integer> result = Map.of(
                LottoRank.FIFTH, 1,
                LottoRank.FOURTH, 0,
                LottoRank.THIRD, 0,
                LottoRank.SECOND, 0,
                LottoRank.FIRST, 0
        );

        String output = captureOutput(() -> outputView.printWinningStatistics(result, 0.35));

        assertThat(output).contains("3개 일치 (5000원)- 1개");
        assertThat(output).contains("총 수익률은 0.35입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
    }

    @Test
    void 수익률이_1보다_크면_이익_문구를_출력한다() {
        OutputView outputView = new OutputView();
        Map<LottoRank, Integer> result = Map.of(
                LottoRank.FIFTH, 0,
                LottoRank.FOURTH, 0,
                LottoRank.THIRD, 1,
                LottoRank.SECOND, 0,
                LottoRank.FIRST, 0
        );

        String output = captureOutput(() -> outputView.printWinningStatistics(result, 2.50));

        assertThat(output).contains("총 수익률은 2.50입니다.(기준이 1이기 때문에 결과적으로 이익이라는 의미임)");
    }

    private String captureOutput(Runnable action) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            System.setOut(new PrintStream(outputStream));
            action.run();
            return outputStream.toString();
        } finally {
            System.setOut(originalOut);
        }
    }
}
