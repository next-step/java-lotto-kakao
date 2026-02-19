package lotto;

import lotto.domain.LottoNumber;
import lotto.domain.LottoResult;
import lotto.domain.LottoStatus;
import lotto.generator.CompositeLottoGenerator;
import lotto.generator.LottoGenerator;
import lotto.generator.NumberGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static lotto.domain.LottoStatus.*;
import static lotto.domain.LottoStatus.FIVE_CORRECT;
import static lotto.domain.LottoStatus.FIVE_CORRECT_BONUS;
import static lotto.domain.LottoStatus.SIX_CORRECT;

public class LottoControllerTest {

    @Test
    @DisplayName("통합 테스트")
    public void success() {
        NumberGenerator fixedNumberGenerator = new FixedNumberGenerator();
        CompositeLottoGenerator lottoGenerator = new CompositeLottoGenerator();
        MockInputView inputView = new MockInputView(List.of(
                "3000",
                "2",
                "1,2,3,4,5,6",
                "1,2,3,4,5,6",
                "1,2,3,4,5,6",
                "7"
        ));
        MockOutputView outputView = new MockOutputView();

        LottoController controller = new LottoController(
                fixedNumberGenerator,
                lottoGenerator,
                inputView,
                outputView
        );

        LottoResult lottoResult = controller.play();

        Assertions.assertThat(lottoResult).isNotNull();
        Assertions.assertThat(outputView.getOutput()).containsExactly(
                "구입금액을 입력해 주세요.",
                "수동으로 구매할 로또 수를 입력해 주세요.",
                "수동으로 구매할 번호를 입력해 주세요.",
                "수동으로 2장, 자동으로 1개를 구매했습니다.",
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

    static class FixedNumberGenerator implements NumberGenerator {

        @Override
        public List<Integer> generate() {
            return List.of(1, 2, 3, 4, 5, 6);
        }
    }

    static class MockInputView implements InputView {

        private Queue<String> queue;

        public MockInputView(List<String> inputs) {
            this.queue = new LinkedList<>(inputs);
        }

        @Override
        public String inputManualLottoCount() {
            return queue.poll();
        }

        @Override
        public String inputPrice() {
            return queue.poll();
        }

        @Override
        public String inputManualLotto() {
            return queue.poll();
        }

        @Override
        public String inputWinningLotto() {
            return queue.poll();
        }

        @Override
        public String inputBonusNumber() {
            return queue.poll();
        }
    }

    static class MockOutputView implements OutputView {

        public List<String> output;

        public MockOutputView() {
            this.output = new ArrayList<>();
        }

        @Override
        public void printLog(List<LottoNumber> list) {
            String result = list.stream()
                    .map(lottoNumber -> String.valueOf(lottoNumber.getNumber()))
                    .collect(Collectors.joining(", ", "[", "]"));

            output.add(result);
        }

        @Override
        public void printPriceMessage() {
            output.add("구입금액을 입력해 주세요.");
        }

        @Override
        public void printLottoCountMessage(int manualLottoCount, int autoLottoCount) {
            output.add("수동으로 " + manualLottoCount + "장, 자동으로 " + autoLottoCount + "개를 구매했습니다.");
        }

        @Override
        public void printStatistics(Map<LottoStatus, Integer> statuses) {
            output.add("당첨 통계");
            output.add("---------");
            output.add("3개 일치 (" + THREE_CORRECT.getPrice() + "원) - " + statuses.getOrDefault(THREE_CORRECT, 0) + "개");
            output.add("4개 일치 (" + FOUR_CORRECT.getPrice() + "원) - " + statuses.getOrDefault(FOUR_CORRECT, 0) + "개");
            output.add("5개 일치 (" + FIVE_CORRECT.getPrice() + "원) - " + statuses.getOrDefault(FIVE_CORRECT, 0) + "개");
            output.add("5개 일치, 보너스 볼 일치 (" + FIVE_CORRECT_BONUS.getPrice() + "원) - " + statuses.getOrDefault(FIVE_CORRECT_BONUS, 0) + "개");
            output.add("6개 일치 (" + SIX_CORRECT.getPrice() + "원) - " + statuses.getOrDefault(SIX_CORRECT, 0) + "개");
        }

        @Override
        public void printProfitRate(double profitRate) {
            output.add("총 수익률은 " + String.format("%.2f", profitRate) + "입니다.");
        }

        @Override
        public void printWinningLottoMessage() {
            output.add("지난 주 당첨 번호를 입력해 주세요.");
        }

        @Override
        public void printBonusNumberMessage() {
            output.add("보너스 볼을 입력해 주세요.");
        }

        @Override
        public void printManualLottoCount() {
            output.add("수동으로 구매할 로또 수를 입력해 주세요.");
        }

        @Override
        public void printManualLottoInputMessage() {
            output.add("수동으로 구매할 번호를 입력해 주세요.");
        }

        public List<String> getOutput() {
            return output;
        }
    }
}
