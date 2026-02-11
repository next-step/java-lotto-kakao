package view;

import java.nio.file.attribute.BasicFileAttributes;

import model.WinLevel;

public class StatBoardView {
	public void showStatResult() {
		System.out.println("당첨 통계");
		System.out.println("---------");
	}

	public void showWinCountMessage(WinLevel winLevel, Integer count) {
		if(winLevel == WinLevel.FIRST)
			System.out.println("3개 일치 (2000000000원) - " + count+ "개");
		if(winLevel == WinLevel.SECOND)
			System.out.println("5개 일치, 보너스 볼 일치 (30000000원) - " + count+ "개");
		if(winLevel == WinLevel.THIRD)
			System.out.println("5개 일치 (1500000원) - " + count+ "개");
		if(winLevel == WinLevel.FOURTH)
			System.out.println("4개 일치 (50000원) - " + count+ "개");
		if(winLevel == WinLevel.FIFTH)
			System.out.println("3개 일치 (5000원) - " + count+ "개");
	}

	public void showProfitMessage(Double profitRatio) {
		System.out.printf("총 수익률은 %.2f입니다.\n", profitRatio);
	}
}
