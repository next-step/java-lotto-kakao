package lotto.view;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class OutputView {

    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8));

    public void write(OutputMessage outputMessage, Object... args) throws IOException {
        bw.write(outputMessage.format(args));
        bw.flush();
    }

    public void write(String message) throws IOException {
        bw.write(message);
        bw.flush();
    }
}
