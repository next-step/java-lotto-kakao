package view;

import java.util.List;
import java.util.Scanner;

import model.Lotto;

public class TicketBoothView {

    private final Scanner SCANNER = new Scanner(System.in);

    public void showInputPriceMessage() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public String inputTicketPrice() {
        return SCANNER.nextLine();
    }

    public void showTicketInfo(List<Lotto> lottos) {
        System.out.println(lottos.size() + "개를 구매했습니다.");
        for (Lotto lotto : lottos) {
            System.out.println(lotto);
        }
    }

    public void showErrorMessage(IllegalArgumentException e) {
        System.out.println("[Error] " + e.getMessage());
    }
}
