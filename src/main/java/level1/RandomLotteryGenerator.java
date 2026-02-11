package level1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import level1.domain.Constant;
import level1.domain.Lotteries;
import level1.domain.Lottery;

public class RandomLotteryGenerator {

    private static final List<Integer> randomNumberList;

    static {
        randomNumberList = IntStream.rangeClosed(
                        Constant.LOTTERY_MIN_VALUE, Constant.LOTTERY_MAX_VALUE
                )
                .boxed().collect(Collectors.toList());
    }

    public Lotteries generate(int num) {
        List<Lottery> randomLotteries = new ArrayList<>(num);

        for (int i = 0; i < num; i++) {
            randomLotteries.add(this.genRandomLottery());
        }

        return new Lotteries(randomLotteries);
    }

    private Lottery genRandomLottery() {
        Collections.shuffle(randomNumberList);

        List<String> randomNumbers = randomNumberList.stream()
                .limit(Constant.LOTTERY_SIZE)
                .map(String::valueOf)
                .toList();

        return new Lottery(randomNumbers);
    }
}
