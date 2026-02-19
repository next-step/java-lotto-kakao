package lotto.domain;

import java.util.List;

public class MyLotto {
    private final List<Lotto> myLottoList;

    public MyLotto(List<Lotto> myLottoList) {
        this.myLottoList = List.copyOf(myLottoList);
    }

    public int getSize() {
        return this.myLottoList.size();
    }

    public Lotto getMyLotto(int index) {
        return this.myLottoList.get(index);
    }

    public String getMyLottoStringType() {
        StringBuilder sb = new StringBuilder();
        for (Lotto lotto : myLottoList) {
            sb.append(lotto.getLottoString());
            sb.append("\n");
        }

        return sb.toString();
    }
}
