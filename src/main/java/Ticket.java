import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Ticket {
	private final List<Integer> numbers;
	Ticket(Integer... numbers) {
		// numbers.length
		List<Integer> result = new ArrayList<>(Arrays.asList(numbers));
		result.sort(((o1, o2) -> o1 - o2));
		this.numbers = result;
	}

	public List<Integer> get() {
		return numbers;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass())
			return false;
		Ticket ticket = (Ticket)o;
		return Objects.equals(numbers, ticket.numbers);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(numbers);
	}
}
