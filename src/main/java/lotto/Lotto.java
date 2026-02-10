package lotto;

import lotto.enums.LottoStatus;

import java.util.*;

public class Lotto {
    private static List<Integer> allNumbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25,
            26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44,45));
    private List<Ball> balls;
    private LottoStatus status;
    private Ball bonus;

    public Lotto() {
        Collections.shuffle(allNumbers);
        balls  = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            balls.add(new Ball(allNumbers.get(i)));
        }
        status = LottoStatus.ZERO;
        bonus = new Ball(0);
    }

    public Lotto(List<Ball> balls, Ball  bonus) {
        Set<Ball> set = new HashSet<Ball>(balls);
        set.add(bonus);
        if(set.size() != 7) {
            throw new IllegalArgumentException();
        }

        this.balls = balls;
        this.status = LottoStatus.ANSWER;
        this.bonus = bonus;
    }

    public List<Ball> getBalls() {
        return balls;
    }
    public LottoStatus getStatus() {return status;}
    public Ball getBonus() {return bonus;}
}
