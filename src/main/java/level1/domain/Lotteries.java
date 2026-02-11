package level1.domain;

import static java.util.stream.Collectors.joining;

import java.util.ArrayList;
import java.util.List;

public class Lotteries {

    private final List<Lottery> lotteries;

    public Lotteries(List<Lottery> lotteries) {
        this.lotteries = new ArrayList<>(lotteries);
    }

    public String representLotteries() {
        return lotteries.stream()
                .map(Lottery::represent)
                .collect(joining("\n"));
    }

    public List<Lottery> getLotteries() {
        return new ArrayList<>(lotteries);
    }
}
