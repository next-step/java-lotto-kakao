package lotto;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoIntegrationTest {
	@DisplayName("정상 입력 시 안내 문구가 출력되어야 한다")
	@Test
	void main_withValidInput_printsExpectedSections() {
		String input = String.join(System.lineSeparator(),
			"2000",
			"1",
			"1, 2, 3, 4, 5, 6",
			"1, 2, 3, 4, 5, 6",
			"7"
		);
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PrintStream originalOut = System.out;
		java.io.InputStream originalIn = System.in;

		System.setIn(in);
		System.setOut(new PrintStream(out));
		try {
			LottoApplication.main(new String[0]);
		} finally {
			System.setIn(originalIn);
			System.setOut(originalOut);
		}

		String output = out.toString();
		assertThat(output).contains("구입금액을 입력해 주세요.");
		assertThat(output).contains("수동으로 구매할 로또 수를 입력해 주세요.");
		assertThat(output).contains("수동으로 구매할 번호를 입력해 주세요.");
		assertThat(output).contains("수동으로");
		assertThat(output).contains("지난 주 당첨 번호를 입력해 주세요.");
		assertThat(output).contains("보너스 볼을 입력해 주세요.");
		assertThat(output).contains("당첨 통계");
		assertThat(output).contains("총 수익률은");
	}
}
