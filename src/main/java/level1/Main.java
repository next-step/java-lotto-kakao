package level1;

import java.util.*;
import java.util.stream.Stream;

public class Main {

    private static final InputHandler inputHandler;
    private static final RandomLotteryGenerator randomLotteryGenerator;

    static {
        inputHandler = new InputHandler();
        randomLotteryGenerator = new RandomLotteryGenerator();
    }

    public static void main(String[] args) {
        Price price = inputHandler.inputPrice();
        int manualInput = inputHandler.inputManualCount();
        PurchaseAmount purchaseAmount = new PurchaseAmount(price, manualInput);

        Lotteries userLotteries = purchaseAll(purchaseAmount, purchaseManualLotteries(purchaseAmount.getManual()));

        printPurchaseResult(userLotteries);

        AnswerLottery answerLottery = inputHandler.inputAnswerLottery();
        printResult(new Report(price, answerLottery, userLotteries.getLotteries()));
    }

    private static Lotteries purchaseAll(PurchaseAmount purchaseAmount, List<Lottery> manualLotteries) {
        List<Lottery> combined = new ArrayList<>(manualLotteries);
        combined.addAll(randomLotteryGenerator.generate(purchaseAmount.getAuto()).getLotteries());

        System.out.printf("\n수동으로 %d장, 자동으로 %d개를 구매했습니다.\n",
                purchaseAmount.getManual(), purchaseAmount.getAuto());

        return new Lotteries(combined);
    }

    private static List<Lottery> purchaseManualLotteries(int manualCount) {
        if (manualCount <= 0) {
            return Collections.emptyList();
        }

        System.out.println("수동으로 구매할 번호를 입력해 주세요.");

        return Stream.generate(inputHandler::inputManualNumbers)
                .limit(manualCount)
                .map(Lottery::new)
                .toList();
    }

    private static void printPurchaseResult(Lotteries lotteries) {
        System.out.println(lotteries.representLotteries());
        System.out.println();
    }

    private static void printResult(Report report) {
        System.out.println();
        System.out.println(report.representReport());
    }
}