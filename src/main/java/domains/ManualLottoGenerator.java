package domains;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ManualLottoGenerator implements LottoGenerator {
    private final List<Lotto> manualLottos = new ArrayList<>();

    // 컨트롤러나 뷰에서 숫자를 하나씩 받을 때마다 중복 검증
    public void addManualNumbers(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());

        Lotto newLotto = new Lotto(lottoNumbers);

        if (manualLottos.contains(newLotto)) {
            throw new IllegalArgumentException("이미 입력된 수동 로또 번호입니다.");
        }
        manualLottos.add(newLotto);
    }

    @Override
    public List<Lotto> generate() {
        return new ArrayList<Lotto>(manualLottos);
    }
}