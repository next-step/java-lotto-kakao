package lotto;

import lotto.enums.LottoStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LottoView {
    private final Scanner scanner = new Scanner(System.in);

    public String readPrice() {
        return read("구입금액을 입력해주세요.");
    }

    public void printPurchasedLotto(List<Lotto> lottos) {
         print(lottos.size()+"개를 구매했습니다.");
         for (Lotto lotto : lottos) {
             print(lotto.toString());
         }
         print("");
    }

    public String readPreviousLotto() {
        return read("지난 주 당첨 번호를 입력해 주세요.");
    }

    public String readPreviousBonusBall() {
        return read("보너스 볼을 입력해 주세요.");
    }

    public void printResult(User user) {

        List<LottoStatus> winStatuses = new ArrayList<>(List.of(LottoStatus.THREE, LottoStatus.FOUR, LottoStatus.FIVE, LottoStatus.SIX_BONUS,LottoStatus.SIX));

        print("당첨 통계");
        print("---------");
        printDetailResult(user.getResult(), winStatuses);
        System.out.printf("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)",(float)(user.getAward()) / (float)(user.getPrice()));
    }

    private static void printDetailResult(Map<LottoStatus, Integer> result, List<LottoStatus> winStatuses) {
        for (LottoStatus status : winStatuses) {
            String format = "%d개 일치 (%,d원) - %d개%n";

            if (status == LottoStatus.SIX_BONUS) {
                format = "%d개 일치, 보너스 볼 일치 (%,d원) - %d개%n";
            }
            System.out.printf(format,status.getCount(),status.getMoney(), result.getOrDefault(status, 0));
        }
    }

    public void print(String message) {
        System.out.println(message);
    }
    private String read(String message) {
        print(message);
        return scanner.nextLine();
    }

}
