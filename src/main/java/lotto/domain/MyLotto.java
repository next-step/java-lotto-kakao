package lotto.domain;

import lotto.util.NumberAutoCreator;
import lotto.util.NumberCreator;
import lotto.util.NumberManualCreator;

import java.util.ArrayList;
import java.util.List;

public class MyLotto {
    private final List<LottoBalls> myLottoList;

    public MyLotto(List<LottoBalls> myLottoList) {
        this.myLottoList = new ArrayList<>(myLottoList);
    }

    public int getSize() {
        return this.myLottoList.size();
    }

    public LottoBalls getMyLotto(int index) {
        return new LottoBalls(this.myLottoList.get(index));
    }

    public String getMyLottoStringType() {
        StringBuilder sb = new StringBuilder();
        for (LottoBalls lotto : myLottoList) {
            sb.append(lotto.getLottoNumberString());
            sb.append("\n");
        }

        return sb.toString();
    }
}
