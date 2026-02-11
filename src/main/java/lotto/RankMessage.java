package lotto;
import java.util.Locale;

public enum RankMessage {
    FIRST("%d개 일치(%s원)"),
    SECOND("%d개 일치, 보너스 볼 일치 (%s원)"),
    THIRD("%d개 일치 (%s원)"),
    FOURTH("%d개 일치 (%s원)"),
    FIFTH("%d개 일치 (%s원)"),
    MISS("꽝");

    private final String template;

    RankMessage(String template) {
        this.template = template;
    }

    public static String format(Rank rank) {
        return RankMessage.valueOf(rank.name()).render(rank);
    }

    private String render(Rank rank) {
        String money = String.format(Locale.KOREA, "%,d", rank.getWinningMoney());
        return String.format(template, rank.getCountOfMatch(), money);
    }
}


