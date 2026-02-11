package level1;

import level1.domain.AnswerLottery;
import level1.domain.Constant;
import level1.domain.Lotteries;

public class Main {

    private static final InputHandler inputHandler;
    private static final RandomLotteryGenerator randomLotteryGenerator;

    static {
        inputHandler = new InputHandler();
        randomLotteryGenerator = new RandomLotteryGenerator();
    }

    public static void main(String[] args) {
        int price = inputHandler.inputPrice();

        Lotteries userLotteries = purchaseLotteries(price);

        printLotteries(userLotteries);

        AnswerLottery answerLottery = inputHandler.inputAnswerLottery();

        Report report = new Report(
                price, answerLottery, userLotteries.getLotteries()
        );

        printResult(report);
    }

    private static Lotteries purchaseLotteries(int price) {
        int count = price / Constant.LOTTERY_PRICE;

        System.out.println(count + "개를 구매했습니다.");

        return randomLotteryGenerator.generate(count);
    }

    private static void printLotteries(Lotteries lotteries) {
        System.out.println(lotteries.representLotteries());

        System.out.println();
    }

    private static void printResult(Report report) {
        System.out.println();

        System.out.println(report.representReport());
    }
}