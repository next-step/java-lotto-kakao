package model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Vendor {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;

    private static final String EMPTY_MANUAL_COUNT_MESSAGE = "수동 구매 수는 비어 있을 수 없습니다.";
    private static final String INVALID_MANUAL_COUNT_MESSAGE = "수동 구매 수는 0 이상이어야 합니다.";
    private static final String EXCEEDED_MANUAL_COUNT_MESSAGE = "수동 로또 수는 전체 구매 수를 초과할 수 없습니다.";
    private static final String EMPTY_MANUAL_INPUTS_MESSAGE = "수동 로또 번호 입력이 필요합니다.";
    private static final String MISMATCHED_MANUAL_INPUT_COUNT_MESSAGE = "수동 로또 번호 입력 개수가 수동 구매 수와 다릅니다.";
    private static final String EMPTY_MANUAL_NUMBERS_MESSAGE = "수동 로또 번호는 비어 있을 수 없습니다.";
    private static final String INVALID_MANUAL_NUMBERS_SIZE_MESSAGE = "수동 로또 번호는 6개의 숫자여야 합니다.";
    private static final String NON_NUMERIC_MANUAL_NUMBER_MESSAGE = "수동 로또 번호는 숫자여야 합니다.";
    private static final String OUT_OF_RANGE_MANUAL_NUMBER_MESSAGE = "수동 로또 번호는 1~45 사이의 숫자여야 합니다.";
    private static final String DUPLICATED_MANUAL_NUMBER_MESSAGE = "수동 로또 번호는 중복될 수 없습니다.";

    private final AutoLottoGenerator autoLottoGenerator;

    public Vendor(){
        autoLottoGenerator = new AutoLottoGenerator();
    }

    public Lottos sell(int money){
        return sell(new Money(money));
    }

    public Lottos sell(Money money){
        LottosGenerator lottosGenerator = new AutoLottosGenerator(autoLottoGenerator, money.toLottoCount());
        return lottosGenerator.generate();
    }

    public Lottos sell(Money money, Integer manualLottoCount, List<String> manualLottoInputs) {
        validateManualLottoCount(manualLottoCount, money.toLottoCount());
        validateManualInputCount(manualLottoInputs, manualLottoCount);

        List<Lotto> manualLottos = parseManualLottos(manualLottoInputs);
        int autoLottoCount = money.toLottoCount() - manualLottoCount;
        LottosGenerator lottosGenerator = new CompositeLottosGenerator(
                new ManualLottosGenerator(manualLottos),
                new AutoLottosGenerator(autoLottoGenerator, autoLottoCount)
        );
        return lottosGenerator.generate();
    }

    private void validateManualLottoCount(Integer manualLottoCount, Integer totalLottoCount) {
        if (manualLottoCount == null) {
            throw new IllegalArgumentException(EMPTY_MANUAL_COUNT_MESSAGE);
        }
        if (manualLottoCount < 0) {
            throw new IllegalArgumentException(INVALID_MANUAL_COUNT_MESSAGE);
        }
        if (manualLottoCount > totalLottoCount) {
            throw new IllegalArgumentException(EXCEEDED_MANUAL_COUNT_MESSAGE);
        }
    }

    private void validateManualInputCount(List<String> manualLottoInputs, Integer manualLottoCount) {
        if (manualLottoInputs == null) {
            throw new IllegalArgumentException(EMPTY_MANUAL_INPUTS_MESSAGE);
        }
        if (manualLottoInputs.size() != manualLottoCount) {
            throw new IllegalArgumentException(MISMATCHED_MANUAL_INPUT_COUNT_MESSAGE);
        }
    }

    private List<Integer> parseManualLottoNumbers(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(EMPTY_MANUAL_NUMBERS_MESSAGE);
        }

        List<String> numberTokens = Arrays.stream(input.split(","))
                .map(String::trim)
                .collect(Collectors.toList());

        if (numberTokens.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(INVALID_MANUAL_NUMBERS_SIZE_MESSAGE);
        }

        try {
            List<Integer> numbers = numberTokens.stream()
                    .map(this::parseManualNumber)
                    .collect(Collectors.toList());

            validateManualNumberRange(numbers);
            validateManualNumberUniqueness(numbers);
            return numbers;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NON_NUMERIC_MANUAL_NUMBER_MESSAGE);
        }
    }

    private List<Lotto> parseManualLottos(List<String> manualLottoInputs) {
        return manualLottoInputs.stream()
                .map(this::parseManualLottoNumbers)
                .map(numbers -> new Lotto(numbers.toArray(new Integer[0])))
                .collect(Collectors.toList());
    }

    private Integer parseManualNumber(String numberToken) {
        if (numberToken.isEmpty()) {
            throw new NumberFormatException();
        }
        return Integer.parseInt(numberToken);
    }

    private void validateManualNumberRange(List<Integer> numbers) {
        for (Integer number : numbers) {
            if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
                throw new IllegalArgumentException(OUT_OF_RANGE_MANUAL_NUMBER_MESSAGE);
            }
        }
    }

    private void validateManualNumberUniqueness(List<Integer> numbers) {
        Set<Integer> manualNumberSet = new HashSet<>();
        for (Integer number : numbers) {
            if (!manualNumberSet.add(number)) {
                throw new IllegalArgumentException(DUPLICATED_MANUAL_NUMBER_MESSAGE);
            }
        }
    }
}
