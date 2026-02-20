package domains;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutoLottoGenerator implements LottoGenerator {
    private final int count;
    // LottoNumber의 캐시된 전체 목록을 가져옵니다.
    private static final List<LottoNumber> ALL_NUMBERS = LottoNumber.values();

    public AutoLottoGenerator(int count) {
        this.count = count;
    }

    @Override
    public List<Lotto> generate() {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(generateRandomLotto());
        }
        return lottos;
    }

    private Lotto generateRandomLotto() {
        List<LottoNumber> numbers = new ArrayList<>(ALL_NUMBERS);
        Collections.shuffle(numbers);

        // 앞 6개 자르기
        List<LottoNumber> selected = new ArrayList<>(numbers.subList(0, 6));

        // Lotto 생성자에서 정렬하므로 여기선 그대로 넘김
        return new Lotto(selected);
    }
}