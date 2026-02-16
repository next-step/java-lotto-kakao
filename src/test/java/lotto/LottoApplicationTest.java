package lotto;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.LottoStatus;
import lotto.domain.Lottos;
import lotto.domain.pick.LottoPickStrategy;
import lotto.domain.pick.ManualLottoNumberGenerator;
import lotto.domain.service.AutoLottoService;
import lotto.view.input.InputView;
import lotto.view.output.OutputView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import static lotto.domain.LottoStatus.FIVE_CORRECT;
import static lotto.domain.LottoStatus.FIVE_CORRECT_BONUS;
import static lotto.domain.LottoStatus.FOUR_CORRECT;
import static lotto.domain.LottoStatus.SIX_CORRECT;
import static lotto.domain.LottoStatus.THREE_CORRECT;
import static org.assertj.core.api.Assertions.assertThat;

public class LottoApplicationTest {

    @Test
    @DisplayName("자동 로또 생성기. 통합 테스트")
    public void success_auto() {
        Lotto lotto1 = new Lotto(1, 2, 3, 4, 5, 6);
        Lotto lotto2 = new Lotto(7, 8, 9, 10, 11, 12);
        Lotto lotto3 = new Lotto(13, 14, 15, 16, 17, 18);
        List<Lotto> lottos = List.of(lotto1, lotto2, lotto3);


        LottoPickStrategy numberGenerator = new FixedNumberGenerator();
        MockInputView inputView = new MockInputView(List.of(
                "3000",
                "1,2,3,4,5,6",
                "7"
        ));
        MockOutputView outputView = new MockOutputView();

        AutoLottoService autoService = new AutoLottoService(numberGenerator, inputView, outputView);
        LottoApplication application = new LottoApplication(
                autoService,
                inputView,
                outputView
        );

        application.play();

        assertThat(outputView.getOutput()).containsExactly(
                "구입금액을 입력해 주세요.",
                "3개를 구매했습니다.",
                "[1, 2, 3, 4, 5, 6]",
                "[1, 2, 3, 4, 5, 6]",
                "[1, 2, 3, 4, 5, 6]",
                "지난 주 당첨 번호를 입력해 주세요.",
                "보너스 볼을 입력해 주세요.",
                "당첨 통계",
                "---------",
                "3개 일치 (5000원) - 0개",
                "4개 일치 (50000원) - 0개",
                "5개 일치 (1500000원) - 0개",
                "5개 일치, 보너스 볼 일치 (30000000원) - 0개",
                "6개 일치 (2000000000원) - 3개",
                "총 수익률은 2000000.00입니다."
        );
    }

    @Test
    @DisplayName("수동 로또 생성기 통합테스트")
    void success_manual() {
        // given

        MockInputView inputView = new MockInputView(List.of(
                "14000",                 // 구입금액
                "3",                     // 수동 구매 수
                "8, 21, 23, 41, 42, 43",  // 수동 로또 1
                "3, 5, 11, 16, 32, 38",   // 수동 로또 2
                "7, 11, 16, 35, 36, 44",  // 수동 로또 3
                "1, 2, 3, 4, 5, 6",       // 지난 주 당첨 번호
                "7"                      // 보너스 볼
        ));
        MockOutputView outputView = new MockOutputView();

        LottoPickStrategy autoGenerator = new FixedNumberGenerator();
        ManualLottoNumberGenerator manualGenerator = new ManualLottoNumberGenerator(inputView);

        lotto.domain.service.ManualLottoService manualService =
                new lotto.domain.service.ManualLottoService(
                        autoGenerator,
                        manualGenerator,
                        inputView,
                        outputView
                );

        LottoApplication application = new LottoApplication(
                manualService,
                inputView,
                outputView
        );

        // when
        application.play();

        // then
        assertThat(outputView.getOutput()).containsExactly(
                "구입금액을 입력해 주세요.",
                "\n수동으로 구매할 로또 수를 입력해 주세요.",
                "\n수동으로 구매할 번호를 입력해 주세요.",
                "\n수동으로 3장, 자동으로 11개를 구매했습니다.",
                "[8, 21, 23, 41, 42, 43]",
                "[3, 5, 11, 16, 32, 38]",
                "[7, 11, 16, 35, 36, 44]",

                // auto 11장: FixedNumberGenerator라 전부 동일
                "[1, 2, 3, 4, 5, 6]",
                "[1, 2, 3, 4, 5, 6]",
                "[1, 2, 3, 4, 5, 6]",
                "[1, 2, 3, 4, 5, 6]",
                "[1, 2, 3, 4, 5, 6]",
                "[1, 2, 3, 4, 5, 6]",
                "[1, 2, 3, 4, 5, 6]",
                "[1, 2, 3, 4, 5, 6]",
                "[1, 2, 3, 4, 5, 6]",
                "[1, 2, 3, 4, 5, 6]",
                "[1, 2, 3, 4, 5, 6]",

                "지난 주 당첨 번호를 입력해 주세요.",
                "보너스 볼을 입력해 주세요.",
                "당첨 통계",
                "---------",
                "3개 일치 (5000원) - 0개",
                "4개 일치 (50000원) - 0개",
                "5개 일치 (1500000원) - 0개",
                "5개 일치, 보너스 볼 일치 (30000000원) - 0개",
                "6개 일치 (2000000000원) - 11개",
                "총 수익률은 1571428.57입니다."
        );

    }


    static class FixedNumberGenerator implements LottoPickStrategy {

        @Override
        public List<LottoNumber> generate() {
            return List.of(
                    LottoNumber.of(1),
                    LottoNumber.of(2),
                    LottoNumber.of(3),
                    LottoNumber.of(4),
                    LottoNumber.of(5),
                    LottoNumber.of(6)
            );
        }
    }

    static class MockInputView implements InputView {

        private final Queue<String> queue;

        public MockInputView(List<String> inputs) {
            this.queue = new LinkedList<>(inputs);
        }

        @Override
        public String input() {
            return queue.poll();
        }

        @Override
        public int inputNumber() {
            String line = queue.poll();
            if (line == null) return 0;
            return Integer.parseInt(line.trim());
        }

        @Override
        public List<Integer> inputNumbers(String delimiter) {
            String line = queue.poll();
            if (line == null) return List.of();

            String[] tokens = line.split(delimiter);
            List<Integer> result = new ArrayList<>();
            for (String token : tokens) {
                result.add(Integer.parseInt(token.trim())); // ✅ 공백 안전
            }
            return result;
        }
    }

    static class MockOutputView implements OutputView {

        private final List<String> output = new ArrayList<>();

        @Override
        public void printMessage(String message) {
            output.add(message);
        }

        @Override
        public void printLottos(Lottos lottos) {
            for (Lotto lotto : lottos.asList()) {
                output.add(lotto.toString());
            }
        }

        @Override
        public void printWinningStatistics(
                final Map<LottoStatus, Integer> statuses,
                double profitRate
        ) {
            printMessage("당첨 통계");
            printMessage("---------");

            printStatusLine(THREE_CORRECT, "3개 일치", statuses);
            printStatusLine(FOUR_CORRECT, "4개 일치", statuses);
            printStatusLine(FIVE_CORRECT, "5개 일치", statuses);
            printStatusLine(FIVE_CORRECT_BONUS, "5개 일치, 보너스 볼 일치", statuses);
            printStatusLine(SIX_CORRECT, "6개 일치", statuses);

            printMessage("총 수익률은 " + String.format("%.2f입니다.", profitRate));
        }

        @Override
        public void printManualCountRequest() {
            output.add("\n수동으로 구매할 로또 수를 입력해 주세요.");
        }

        @Override
        public void printManualLottoRequest() {
            output.add("\n수동으로 구매할 번호를 입력해 주세요.");
        }

        @Override
        public void printAutoNumberRequest() {
            output.add("\n자동으로 구매할 로또 수를 입력해 주세요.");
        }

        @Override
        public void printPriceRequest() {
            output.add("구입금액을 입력해 주세요.");
        }

        @Override
        public void printAutoBuyResult(int count) {
            output.add(count + "개를 구매했습니다.");
        }

        @Override
        public void printManualBuyResult(int manualCount, int totalCount) {
            int autoCount = totalCount - manualCount;
            output.add("\n수동으로 " + manualCount + "장, 자동으로 " + autoCount + "개를 구매했습니다.");
        }

        private void printStatusLine(LottoStatus status, String label, Map<LottoStatus, Integer> statuses) {
            int count = statuses.getOrDefault(status, 0);
            printMessage(label + " (" + status.getPrice() + "원) - " + count + "개");
        }

        public List<String> getOutput() {
            return output;
        }
    }

}
