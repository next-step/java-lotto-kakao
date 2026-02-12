package lotto;

public class Main {
	public static void main(String[] args) {
		LottoController controller = new LottoController(new LottoView(), new Parser());
		controller.run();
	}
}
