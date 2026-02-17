package lotto;

import lotto.domain.LottoNumber;
import lotto.domain.LottoStatus;
import lotto.domain.Lottos;
import lotto.domain.pick.LottoPickStrategy;
import lotto.domain.service.AutoLottoService;
import lotto.view.input.InputView;
import lotto.view.output.OutputView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class LottoApplicationTest {

    @Test
    @DisplayName("playauto: UI 문자열에 의존하지 않고 구매/당첨 수 통계를 OutputView 포트로 전달한다.")
    void playAuto_portLeveltest() {
        FixedPickStrategy fixedPickStrategy = new FixedPickStrategy();

        AutoLottoService service = new AutoLottoService(fixedPickStrategy);
        new


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

        private StubInputView(Queue<Integer> numbers, Queue<List<Integer>> numberLists, Queue<String> inputs) {
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
        public List<Integer> inputNumbers(String delimiter) {
            // delimiter는 UI 파싱 책임(터미널/웹)에 가깝기 때문에,
            // 유스케이스 테스트에서는 "이미 파싱된 값"을 반환하도록 둔다.
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
