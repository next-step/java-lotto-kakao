package com.kakao.onboarding.precourse.albusduke.lotto.util;

import com.kakao.onboarding.precourse.albusduke.lotto.domain.Prize;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.PurchaseGameAmount;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.Statistics;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.WinningPrizes;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.LottoGames;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.LottoNumber;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.LottoNumbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class Console implements Input, Output {
    private static final String NUMBER_FORMAT_ERROR_MSG = "숫자 형식이어야 합니다.";
    private static final String DELIMITER = ",";
    private static final String INTEGER_LIST_FORMAT_ERROR_MSG = "숫자 리스트 형식이어야 합니다. (ex: 1, 2, 3, 4, 5, 6)";

    private static final String PURCHASE_COUNT_FORMAT = "%d개를 구매했습니다.\n";
    private static final String MANUAL_AUTO_PURCHASE_FORMAT = "수동으로 %d장, 자동으로 %d개를 구매했습니다.\n";
    private static final String MANUAL_LOTTO_NUMBERS_PROMPT = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String STATISTICS_PREFIX = "당첨 통계\n-------------";
    private static final String MATCHING_FORMAT = "%d개 일치";
    private static final String REWARD_FORMAT = "(%d원)";
    private static final String BONUS_MATCHING_FORMAT = "보너스 볼 일치";
    private static final String COUNT_FORMAT = "%d개";
    private static final String RATIO_FORMAT = "총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)\n";
    private static final String ERROR_PREFIX = "[ERROR] ";

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String readNext() {
        return scanner.nextLine().trim();
    }

    @Override
    public int readInt() {
        try {
            String next = readNext();
            return Integer.parseInt(next.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NUMBER_FORMAT_ERROR_MSG);
        }
    }

    @Override
    public List<Integer> readIntegerList() {
        String next = readNext();
        try {
            return Arrays.stream(next.split(DELIMITER))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INTEGER_LIST_FORMAT_ERROR_MSG);
        }
    }

    @Override
    public void outputPurchaseGameAmount(PurchaseGameAmount purchasedGameAmount) {
        System.out.printf(PURCHASE_COUNT_FORMAT, purchasedGameAmount.count());
    }

    @Override
    public void outputManualLottoNumbersPrompt() {
        System.out.println(MANUAL_LOTTO_NUMBERS_PROMPT);
    }

    @Override
    public void outputPurchaseCount(int manualCount, int autoCount) {
        System.out.printf(MANUAL_AUTO_PURCHASE_FORMAT, manualCount, autoCount);
    }

    @Override
    public void outputLottoNumbers(LottoGames lottoGames) {
        for (LottoNumbers game : lottoGames.games()) {
            StringJoiner sj = new StringJoiner(", ", "[", "]");
            List<LottoNumber> sortedLottoNumbers = new ArrayList<>(game.getLottoNumbers());
            Collections.sort(sortedLottoNumbers);
            for (LottoNumber number : sortedLottoNumbers) {
                sj.add(String.valueOf(number.getNumber()));
            }
            System.out.println(sj.toString());
        }
    }

    @Override
    public void outputStatistics(Statistics statistics) {
        WinningPrizes winningPrizes = statistics.winningPrizes();
        System.out.println(STATISTICS_PREFIX);
        System.out.println(formatPrizeOutput(Prize.FIFTH, winningPrizes.getCounts().getOrDefault(Prize.FIFTH, 0)));
        System.out.println(formatPrizeOutput(Prize.FORTH, winningPrizes.getCounts().getOrDefault(Prize.FORTH, 0)));
        System.out.println(formatPrizeOutput(Prize.THIRD, winningPrizes.getCounts().getOrDefault(Prize.THIRD, 0)));
        System.out.println(formatPrizeOutput(Prize.SECOND, winningPrizes.getCounts().getOrDefault(Prize.SECOND, 0)));
        System.out.println(formatPrizeOutput(Prize.FIRST, winningPrizes.getCounts().getOrDefault(Prize.FIRST, 0)));
        System.out.printf(RATIO_FORMAT, statistics.ratio());
    }

    private String formatPrizeOutput(Prize prize, int count) {
        return String.format(MATCHING_FORMAT, prize.getMatchingCount()) +
                (prize.hasBonusMatch() ? (", " + BONUS_MATCHING_FORMAT) : "") +
                String.format(REWARD_FORMAT, prize.getReward()) +
                " - " +
                String.format(COUNT_FORMAT, count);
    }

    @Override
    public void outputError(IllegalArgumentException e) {
        System.out.println(ERROR_PREFIX + e.getMessage());
    }
}
