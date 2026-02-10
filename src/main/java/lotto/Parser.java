package lotto;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private static String delimiter = ", ";

    public List<Ball> parse(String input) {
        String[] lines = input.split(delimiter);
        if (lines.length != 6) {
            throw new IllegalArgumentException("입력이 잘못되었습니다: ");
        }

        return makeBalls(lines);
    }

    private List<Ball> makeBalls(String[] lines) {
        List<Ball> balls  = new ArrayList<>();
        try{
            makeBall(lines, balls);
        }
        catch(Exception e) {
            throw new IllegalArgumentException("입력이 잘못되었습니다: ");
        }
        return balls;
    }

    private static void makeBall(String[] lines, List<Ball> balls) {
        for(String line : lines) {
            balls.add(new Ball(line));
        }
    }
}
