package lotto;

import lotto.domain.LottoNumber;
import lotto.domain.LottoStatus;
import lotto.domain.Lottos;
import lotto.domain.pick.LottoPickStrategy;
import lotto.domain.service.AutoLottoService;
import lotto.domain.service.ManualLottoService;
import lotto.view.input.InputView;
import lotto.view.output.OutputView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

public class LottoApplicationTest {

    @Test
    @DisplayName("playauto: UI 문자열에 의존하지 않고 구매/당첨 수 통계를 OutputView 포트로 전달한다.")
    void playAuto_portLevelTest() {
        FixedPickStrategy fixedPickStrategy = new FixedPickStrategy();

        AutoLottoService service = new AutoLottoService(fixedPickStrategy);
        StubInputView inputView = StubInputView.builder()
                .inputNumbers(3000) // 구입금액
                .inputNumbersList(List.of(1, 2, 3, 4, 5, 6))
                .inputs("7")
                .build();

        SpyOutputView outputView = new SpyOutputView();
        LottoApplication app = new LottoApplication(service, inputView, outputView);

        //when
        app.playAuto();

        assertThat(outputView.priceRequestCount).isEqualTo(1);
        assertThat(outputView.autoBuyCount).isEqualTo(3);

        assertThat(outputView.printedLottos).isNotNull();
        assertThat(outputView.printedLottos.size()).isEqualTo(3);

        assertThat(outputView.lastWeekWinningNumberRequestCount).isEqualTo(1);
        assertThat(outputView.bonusNumberRequestCount).isEqualTo(1);

        assertThat(outputView.statuses.getOrDefault(LottoStatus.SIX_CORRECT, 0)).isEqualTo(3);
        assertThat(outputView.profitRate).isCloseTo(2_000_000.0, within(1e-9));
    }

    @Test
    @DisplayName("playManual: 수동 3장 + 자동 11장 구매 후 통계를 OutputView 포트로 전달한다.")
    void playManual_portLevelTest() {
        LottoPickStrategy fixedPickStrategy = new FixedPickStrategy();

        ManualLottoService service = new ManualLottoService(fixedPickStrategy);
        StubInputView inputView = StubInputView.builder()
                .inputNumbers(14000) // 구입 금액 -> 14장
                .inputNumbers(3) // 수동 구매 수
                // 수동 로또 3장
                .inputNumbersList(List.of(8, 21, 23, 41, 42, 43))
                .inputNumbersList(List.of(3, 5, 11, 16, 32, 38))
                .inputNumbersList(List.of(7, 11, 16, 35, 36, 44))
                // 지난 주 당첨 번호 + 보너스
                .inputNumbersList(List.of(1, 2, 3, 4, 5, 6))
                .inputs("7")
                .build();
        SpyOutputView outputView = new SpyOutputView();

        LottoApplication app = new LottoApplication(service, inputView, outputView);

        // when
        app.playManual();

        // then
        assertThat(outputView.priceRequestCount).isEqualTo(1);
        assertThat(outputView.manualCountRequestCount).isEqualTo(1);
        assertThat(outputView.manualLottoRequestCount).isEqualTo(1);

        assertThat(outputView.manualBuyManualCount).isEqualTo(3);
        assertThat(outputView.manualBuyTotalCount).isEqualTo(14);

        assertThat(outputView.printedLottos).isNotNull();
        assertThat(outputView.printedLottos.size()).isEqualTo(14);

        assertThat(outputView.statuses.getOrDefault(LottoStatus.SIX_CORRECT, 0)).isEqualTo(11);

        double expectedProfitRate = 22_000_000_000L / 14_000.0;
        assertThat(outputView.profitRate).isCloseTo(expectedProfitRate, within(1e-9));
    }



    /**
     * 테스트를 UI(콘솔/웹)에 덜 묶기 위한 Stub/Spy.
     * - StubInputView: 문자열 파싱을 전혀 하지 않고, "타입이 있는 값"을 그대로 반환한다.
     * - SpyOutputView: 출력 문자열이 아니라, OutputView 메서드 호출과 전달된 데이터를 기록한다.
     */
    static class StubInputView implements InputView {

        private final Queue<Integer> numbers;
        private final Queue<List<Integer>> numberLists;
        private final Queue<String> inputs;

        private StubInputView(
                final Queue<Integer> numbers,
                final Queue<List<Integer>> numberLists,
                final Queue<String> inputs
        ) {
            this.numbers = numbers;
            this.numberLists = numberLists;
            this.inputs = inputs;
        }

        public static Builder builder() {
            return new Builder();
        }

        @Override
        public String input() {
            return inputs.remove();
        }

        @Override
        public int inputNumber() {
            return numbers.remove();
        }

        @Override
        public List<Integer> inputNumbers() {
            return numberLists.remove();
        }

        static class Builder {
            private final Queue<Integer> numbers = new ArrayDeque<>();
            private final Queue<List<Integer>> numberLists = new ArrayDeque<>();
            private final Queue<String> inputs = new ArrayDeque<>();

            Builder inputNumbers(int value) {
                numbers.add(value);
                return this;
            }

            Builder inputNumbersList(List<Integer> values) {
                numberLists.add(List.copyOf(values));
                return this;
            }

            Builder inputs(String value) {
                inputs.add(value);
                return this;
            }

            StubInputView build() {
                return new StubInputView(numbers, numberLists, inputs);
            }
        }
    }

    static class SpyOutputView implements OutputView {

        int priceRequestCount;
        int manualCountRequestCount;
        int manualLottoRequestCount;
        int autoNumberRequestCount;
        int lastWeekWinningNumberRequestCount;
        int bonusNumberRequestCount;

        Integer autoBuyCount;
        Integer manualBuyManualCount;
        Integer manualBuyTotalCount;

        Lottos printedLottos;

        Map<LottoStatus, Integer> statuses = new EnumMap<>(LottoStatus.class);
        Double profitRate;

        @Override
        public void printMessage(String message) {
            // 애플리케이션 유스케이스 테스트에서는 문자열에 의존하지 않는다.
        }

        @Override
        public void printLottos(Lottos lottos) {
            this.printedLottos = lottos;
        }

        @Override
        public void printWinningStatistics(Map<LottoStatus, Integer> statuses, double profitRate) {
            this.statuses = new EnumMap<>(LottoStatus.class);
            this.statuses.putAll(statuses); // 방어적 복사
            this.profitRate = profitRate;
        }

        @Override
        public void printManualCountRequest() {
            manualCountRequestCount++;
        }

        @Override
        public void printManualLottoRequest() {
            manualLottoRequestCount++;
        }

        @Override
        public void printAutoNumberRequest() {
            autoNumberRequestCount++;
        }

        @Override
        public void printPriceRequest() {
            priceRequestCount++;
        }

        @Override
        public void printAutoBuyResult(int count) {
            this.autoBuyCount = count;
        }

        @Override
        public void printManualBuyResult(int manualCount, int totalCount) {
            this.manualBuyManualCount = manualCount;
            this.manualBuyTotalCount = totalCount;
        }

        @Override
        public void printLastWeekWinningNumberRequest() {
            lastWeekWinningNumberRequestCount++;
        }

        @Override
        public void printBonusNumberRequest() {
            bonusNumberRequestCount++;
        }
    }

    static class FixedPickStrategy implements LottoPickStrategy {

        private static final List<LottoNumber> FIXED =
                List.of(1, 2, 3, 4, 5, 6).stream().map(LottoNumber::of).toList();

        @Override
        public List<LottoNumber> generate() {
            return FIXED;
        }
    }
}
