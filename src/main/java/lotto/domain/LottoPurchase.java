package lotto.domain;

import static lotto.domain.LottoPolicy.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import lombok.Getter;

@Getter
public class LottoPurchase {
	private final List<Lotto> lottos;
	private final int manualCount;
	private final int autoCount;
	private final int amount;

	private LottoPurchase(List<Lotto> lottos, int manualCount, int autoCount, int amount) {
		this.lottos = Collections.unmodifiableList(new ArrayList<>(lottos));
		this.manualCount = manualCount;
		this.autoCount = autoCount;
		this.amount = amount;
	}

	public static LottoPurchase of(List<Lotto> lottos, int manualCount, int autoCount, int amount) {
		return new LottoPurchase(lottos, manualCount, autoCount, amount);
	}

	public static void validateAmount(int amount) {
		if (amount < LOTTO_PRICE) {
			throw new IllegalArgumentException(String.format("구입 금액은 %d원 이상이어야 합니다.", LOTTO_PRICE));
		}
	}

	public static void validateManualCount(int amount, int manualCount) {
		if (manualCount < 0) {
			throw new IllegalArgumentException("수동 구매 수량은 0 이상이어야 합니다.");
		}
		int totalCount = amount / LOTTO_PRICE;
		if (manualCount > totalCount) {
			throw new IllegalArgumentException("수동 구매 수량이 구매 가능한 수량을 초과했습니다.");
		}
	}

	public static void validateManualLottosCount(int manualCount, List<Lotto> manualLottos) {
		if (manualLottos == null) {
			throw new IllegalArgumentException("수동 번호는 null일 수 없습니다.");
		}
		if (manualLottos.stream().anyMatch(Objects::isNull)) {
			throw new IllegalArgumentException("수동 번호에 null이 포함될 수 없습니다.");
		}
		if (manualLottos.size() != manualCount) {
			throw new IllegalArgumentException("수동 번호 개수가 수동 구매 수량과 일치하지 않습니다.");
		}
	}
}
