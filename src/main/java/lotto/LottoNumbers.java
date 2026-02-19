package lotto;

import java.util.*;

public class LottoNumbers {

    private List<LottoNumber> lottoNumberList;


    public LottoNumbers(List<Integer> numbers) {
        validateSize(numbers);
        List<LottoNumber> lottoNumbers = new ArrayList<>();

        for (Integer number : numbers) {
            validateDistinctNumber(lottoNumberList, number);
            lottoNumbers.add(LottoNumber.from(number));
        }
        this.lottoNumberList = lottoNumbers;
        sortLottoNumberList();
    }

    public List<LottoNumber> getLottoNumberList() {
        return List.copyOf(lottoNumberList);
    }

    private void generateRandomNumbers() {
        List<LottoNumber> numbers = new ArrayList<>();

        for (int i = 1; i <= 45; i++) {
            numbers.add(LottoNumber.from(i));
        }
        Collections.shuffle(numbers);

        this.lottoNumberList = numbers.subList(0, 6);
    }

    private void sortLottoNumberList() {
        this.lottoNumberList.sort(new Comparator<LottoNumber>() {
            @Override
            public int compare(LottoNumber o1, LottoNumber o2) {
                return Integer.compare(o1.getNumber(), o2.getNumber());
            }
        });
    }
    
    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumberList.contains(lottoNumber);
    }

    public void validateDistinctNumber(List<LottoNumber> lottoNumberList, Integer number) {
        if(lottoNumberList == null) return;
        if(lottoNumberList.contains(LottoNumber.from(number))) throw new IllegalArgumentException("로또에 중복된 숫자가 존재합니다.");
    }

    public void validateSize(List<Integer> numbers) {
        if(numbers.size() != 6) throw new IllegalArgumentException("6개의 숫자를 입력해야 합니다.");
    }
}
