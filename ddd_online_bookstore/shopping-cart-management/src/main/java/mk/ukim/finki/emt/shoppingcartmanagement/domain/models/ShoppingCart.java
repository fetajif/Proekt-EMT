package mk.ukim.finki.emt.shoppingcartmanagement.domain.models;

import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.financial.Money;
import mk.ukim.finki.emt.shoppingcartmanagement.domain.valueobjects.Book;
import mk.ukim.finki.emt.shoppingcartmanagement.domain.valueobjects.UserId;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "shopping_carts")
@Getter
public class ShoppingCart extends AbstractEntity<ShoppingCartId> {

    private LocalDateTime dateCreated;

    @Enumerated(EnumType.STRING)
    private ShoppingCartState state;

    @Column(name = "cart_currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;

//    @AttributeOverride(name = "id", column = @Column(name = "user_id", nullable = false))
//    private UserId userId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<BookItem> bookItemList = new HashSet<>();

    private ShoppingCart() {
        super(ShoppingCartId.randomId(ShoppingCartId.class));
    }

    public ShoppingCart(LocalDateTime dateCreated, Currency currency){
        super(ShoppingCartId.randomId(ShoppingCartId.class));
        this.dateCreated = dateCreated;
        this.currency = currency;
        this.state = ShoppingCartState.PROCESSING;
//        this.userId = userId;
    }

    public Money total() {
        return bookItemList.stream()
                .map(BookItem::subtotal)
                .reduce(new Money(currency, 0), Money::add);
    }

    public BookItem addItem(@NonNull Book book, int qty) {
        Objects.requireNonNull(book, "Book must not be null");
        var item = new BookItem(book.getId(), book.getPrice(), qty);
        bookItemList.add(item);
        return item;
    }

    public void removeItem(@NonNull BookItemId bookItemId) {
        Objects.requireNonNull(bookItemId, "BookItemId must not be null");
        bookItemList.removeIf(v -> v.getId().equals(bookItemId));
    }
}
