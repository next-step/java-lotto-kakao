package lotto;

public class Main {
    public static void main(String[] args) {
        LottoConfig config = new LottoConfig();
        LottoApplication app = config.manualApp();
        app.playManual();
    }
}
