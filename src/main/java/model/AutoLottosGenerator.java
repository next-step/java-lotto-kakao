package model;

public class AutoLottosGenerator implements LottosGenerator {
    private final AutoLottoGenerator autoLottoGenerator;
    private final int lottoCount;

    public AutoLottosGenerator(AutoLottoGenerator autoLottoGenerator, int lottoCount) {
        this.autoLottoGenerator = autoLottoGenerator;
        this.lottoCount = lottoCount;
    }

    @Override
    public Lottos generate() {
        Lottos lottos = new Lottos();

        for (int i = 0; i < lottoCount; i++) {
            lottos.add(autoLottoGenerator.issueLotto());
        }

        return lottos;
    }
}
