package level1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Lottery {

    protected final List<LottoNumber> lottery;

    public Lottery(List<String> numbers) {
        validateInputSize(numbers);

        List<LottoNumber> lottoNumbers = numbers.stream()
                .distinct()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toCollection(ArrayList::new));

        validateSize(lottoNumbers);
        this.lottery = lottoNumbers;
    }

    private void validateSize(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != Constant.LOTTERY_SIZE) {
            throw new IllegalArgumentException("중복되지 않은 숫자는 6개여야 합니다.");
        }
    }

    private void validateInputSize(List<String> lottoNumbers) {
        if (lottoNumbers.size() != Constant.LOTTERY_SIZE) {
            throw new IllegalArgumentException("6자리의 수를 입력해야 합니다");
        }
    }

    public boolean contains(LottoNumber given) {
        return this.lottery.contains(given);
    }

    public String represent() {
        return lottery.stream()
                .sorted()
                .toList()
                .toString();
    }
}
