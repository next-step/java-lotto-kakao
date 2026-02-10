package lotto.domain;

import java.util.List;

public interface RandomPickStrategy {

    public List<Integer> generate();
}
