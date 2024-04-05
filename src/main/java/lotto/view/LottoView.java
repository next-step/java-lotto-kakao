package lotto.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import lotto.dto.LottoResultDto;
import lotto.dto.TicketDto;

public class LottoView {

    private final Scanner scanner = new Scanner(System.in);

    public int getBudget() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = scanner.nextLine();
        return parseInteger(input);
    }

    public int getManualLottoCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요");
        String input = scanner.nextLine();
        return parseInteger(input);
    }

    public List<List<Integer>> getManualLottoNumbers(int manualLottoCount) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<List<Integer>> manualLottoNumbers = new ArrayList<>();
        for (int i = 0; i < manualLottoCount; i++) {
            String input = scanner.nextLine();

            List<Integer> inputNumbers = Arrays.stream(input.split(","))
                    .map(this::parseInteger)
                    .collect(Collectors.toList());

            manualLottoNumbers.add(inputNumbers);
        }
        return manualLottoNumbers;
    }

    public List<Integer> getNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String input = scanner.nextLine();
        return Arrays.stream(input.split(","))
            .map(this::parseInteger)
            .collect(Collectors.toList());
    }

    public int getBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        String input = scanner.nextLine();
        return parseInteger(input);
    }

    public void printTickets(List<TicketDto> tickets) {
        System.out.printf("%d개 구매했습니다.\n", tickets.size());
        tickets.forEach(ticketDto -> {
            List<Integer> sortedTickets = ticketDto.getNumbers().stream()
                .sorted()
                .collect(Collectors.toList());
            System.out.println(sortedTickets);
        });
    }

    public void printLottoResult(LottoResultDto result) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        result.getLottoResult().forEach((key, value) -> System.out.println(key + " - " + value + "개"));
        System.out.printf("총 수익률은 %.2f 입니다.", result.getResultRate());
    }

    public void printError(Exception exception) {
        System.out.println("[Error] "+ exception.getMessage());
    }

    private int parseInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (Exception e) {
            throw new IllegalArgumentException("유효한 정수값을 입력해주세요.");
        }
    }
}
