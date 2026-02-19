package lotto.generator;

import lotto.domain.Lotto;

import java.util.ArrayList;
import java.util.List;

// composite 클래스
public class CompositeLottoGenerator implements LottoGenerator {

    private List<GeneratorTask> tasks;

    public CompositeLottoGenerator() {
        this.tasks = new ArrayList<>();
    }

    public void add(LottoGenerator lottoGenerator, int count) {
        tasks.add(new GeneratorTask(lottoGenerator, count));
    }

    @Override
    public List<Lotto> generate(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (GeneratorTask task : tasks) {
            lottos.addAll(task.generate());
        }
        return lottos;
    }

    public void clear() {
        this.tasks.clear();
    }
}
