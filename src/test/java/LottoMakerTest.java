import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class LottoMakerTest {

	@Test
	void imTicket() {
		Ticket leftTicket = new Ticket(1,2,3,4,5,6);
		Ticket rightTicket = new Ticket(6,5,4,3,2,1);
		assertThat(leftTicket).isEqualTo(rightTicket);
	}

}