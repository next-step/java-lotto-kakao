package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Lottos implements Iterable<Lotto> {
    List<Lotto> lottoList;

    public Lottos(){
        lottoList = new ArrayList<>();
    }

    public Integer getQuantity(){
        return lottoList.size();
    }

    public List<Lotto> getLottoList(){
        return lottoList;
    }

    public void add(Lotto lotto){
        lottoList.add(lotto);
    }

    @Override
    public Iterator<Lotto> iterator() {
        return lottoList.iterator();
    }
}
