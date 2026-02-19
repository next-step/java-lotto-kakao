package model;

import java.util.HashSet;
import java.util.List;

public class Lotto {
    List<Integer> numbers;
    public Lotto(List<Integer> numbers){
        if(numbers.size() != LottoNumber.LOTTO_SIZE) throw new RuntimeException("로또 번호 개수가 "+LottoNumber.LOTTO_SIZE+"개가 아닙니다.");
        if(!numbers.stream().allMatch(new HashSet<Integer>()::add)) throw new RuntimeException("로또 번호는 중복될 수 없습니다");
        for (Integer number : numbers) {
            throwIfOutOfRange(number);
        }
        this.numbers = numbers;
    }

    private void throwIfOutOfRange(Integer number) {
        if (!isInRange(number)) {
            throw new RuntimeException("로또 번호는 "+LottoNumber.MIN_NUMBER+"~"+LottoNumber.MAX_NUMBER+"까지의 숫자여야 한다.");
        }
    }

    public boolean isInRange(Integer value) {
        return LottoNumber.MIN_NUMBER <= value && value <= LottoNumber.MAX_NUMBER;
    }

    public Integer size(){
        return numbers.size();
    }

    public List<Integer> getNumbers(){
        return numbers;
    }
}
