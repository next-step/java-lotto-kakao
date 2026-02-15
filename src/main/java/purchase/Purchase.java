package purchase;

import money.Money;

public record Purchase<T>(T item, Money paid, Money change) {
}
