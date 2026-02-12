package lotto.domain;

import java.util.ArrayList;

public class LottoResults {
    private final ArrayList<LottoResult> lottoResults = new ArrayList<>();

    public LottoResults(MyLotto myLotto, AnswerLotto answerLotto) {
        for (int i = 0; i < myLotto.getSize(); i++) {
            LottoBalls lottoBalls = myLotto.getMyLotto(i).getLottoBallList();
            lottoResults.add(answerLotto.judge(lottoBalls));
        }
    }

    public ArrayList<Rank> getLottoResultRanks() {
        ArrayList<Rank> ranks = new ArrayList<>();

        for (LottoResult lottoResult : lottoResults) {
            ranks.add(lottoResult.calResult());
        }

        return ranks;
    }

    public int getLottoResultsSize() {
        return this.lottoResults.size();
    }
}
