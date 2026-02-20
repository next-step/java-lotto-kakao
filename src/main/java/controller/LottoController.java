package controller;

import domains.*;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class LottoController {
    public static void run() {
        Money userMoney = retry(() -> new Money(InputView.inputMoney()));

        int manualCount = askManualCount(userMoney);
        int autoCount = userMoney.calculateAutoCount(manualCount);

        ManualLottoGenerator manualGenerator = inputAllManualNumbers(manualCount);
        LottoTickets tickets = new LottoStore().buy(manualGenerator, new AutoLottoGenerator(autoCount));

        OutputView.printLottos(manualCount, autoCount, tickets.lottos());
        processResult(tickets, userMoney);
    }

    private static int askManualCount(Money money) {
        return retry(() -> {
            int count = InputView.inputManualCount();
            money.calculateAutoCount(count); // 검증 포함
            return count;
        });
    }

    private static ManualLottoGenerator inputAllManualNumbers(int count) {
        ManualLottoGenerator generator = new ManualLottoGenerator();
        if (count <= 0) return generator;

        OutputView.printManualLottoInputHeader();
        for (int i = 0; i < count; i++) {
            fillValidManualNumber(generator);
        }
        return generator;
    }

    private static void fillValidManualNumber(ManualLottoGenerator generator) {
        retry(() -> {
            generator.addManualNumbers(InputView.inputManualNumbers());
            return generator;
        });
    }

    private static void processResult(LottoTickets tickets, Money money) {
        WinningLotto winningLotto = askWinningLotto();
        List<Rank> ranks = tickets.match(winningLotto);

        OutputView.printWinning(ranks);
        OutputView.printRateOfReturn(money.calculateRate(ranks));
    }

    private static WinningLotto askWinningLotto() {
        Lotto winningNumbers = retry(() -> {
            List<Integer> numbers = InputView.inputWinningNumbers();
            List<LottoNumber> lottoNumbers = numbers.stream()
                    .map(LottoNumber::new)
                    .collect(Collectors.toList());
            return new Lotto(lottoNumbers);
        });

        return retry(() -> {
            LottoNumber bonus = new LottoNumber(InputView.inputBonusNumber());
            return new WinningLotto(winningNumbers, bonus);
        });
    }

    private static <T> T retry(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                OutputView.printError(e);
            }
        }
    }
}
