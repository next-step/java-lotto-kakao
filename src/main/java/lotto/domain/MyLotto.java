package lotto.domain;

import lotto.util.LottoAutoCreator;
import lotto.util.LottoManualCreator;

import java.util.ArrayList;
import java.util.List;

public class MyLotto {
    private final List<Lotto> myLottoList = new ArrayList<>();

    public MyLotto(int autoSize, int manualSize) {
        for (int i = 0; i < autoSize; i++) {
            myLottoList.add(new Lotto(new LottoAutoCreator()));
        }
        for (int i = 0; i < manualSize; i++) {
            myLottoList.add(new Lotto(new LottoManualCreator()));
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
