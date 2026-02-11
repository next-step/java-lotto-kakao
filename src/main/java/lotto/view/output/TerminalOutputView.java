package lotto.view.output;

import java.util.List;
import java.util.stream.Collectors;

public class TerminalOutputView implements OutputView {

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printLog(List<Integer> list) {
        String result = list.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ", "[", "]"));
        System.out.println(result);
    }
}
