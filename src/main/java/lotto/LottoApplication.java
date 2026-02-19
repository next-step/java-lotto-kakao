package lotto;

import lotto.controller.LottoController;

public class LottoApplication {
	public static void main(String[] args) {
		try {
			LottoController.create().run();
		} catch (RuntimeException exception) {
			System.err.println("[ERROR] 시스템 오류가 발생했습니다.");
			throw exception;
		}
	}
}
