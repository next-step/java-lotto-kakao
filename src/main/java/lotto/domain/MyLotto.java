package lotto.domain;

import lotto.util.NumberAutoCreator;
import lotto.util.NumberManualCreator;

import java.util.ArrayList;
import java.util.List;

public class MyLotto {
    private final List<Lotto> myLottoList = new ArrayList<>();

    public MyLotto(int autoSize, int manualSize) {
        for (int i = 0; i < autoSize; i++) {
            myLottoList.add(new Lotto(new NumberAutoCreator()));
        }
        for (int i = 0; i < manualSize; i++) {
            myLottoList.add(new Lotto(new NumberManualCreator()));
        }
    }

    public int getSize() {
        return this.myLottoList.size();
    }

    public Lotto getMyLotto(int index) {
        return new Lotto(this.myLottoList.get(index));
    }

    public String getMyLottoStringType() {
        StringBuilder sb = new StringBuilder();
        for (Lotto lotto : myLottoList) {
            sb.append(lotto.getLottoNumberString());
            sb.append("\n");
        }

        return sb.toString();
    }
}
