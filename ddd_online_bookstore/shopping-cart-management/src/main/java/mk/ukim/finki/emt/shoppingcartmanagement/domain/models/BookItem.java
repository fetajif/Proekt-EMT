package mk.ukim.finki.emt.shoppingcartmanagement.domain.models;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.base.DomainObjectId;
import mk.ukim.finki.emt.sharedkernel.financial.Money;
import mk.ukim.finki.emt.shoppingcartmanagement.domain.valueobjects.BookId;
import org.springframework.lang.NonNull;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "book_item")
@Getter
public class BookItem extends AbstractEntity<BookItemId> {

    private Money itemPrice;

    @Column(name = "qty", nullable = false)
    private int quantity;

    @AttributeOverride(name = "id", column = @Column(name = "book_id", nullable = false))
    private BookId bookId;

    private BookItem() {
        super(DomainObjectId.randomId(BookItemId.class));
    }

    public BookItem(@NonNull BookId bookId, @NonNull Money itemPrice, int qty) {
        super(DomainObjectId.randomId(BookItemId.class));
        this.bookId = bookId;
        this.itemPrice = itemPrice;
        this.quantity = qty;
    }

    public Money subtotal() {
        return itemPrice.multiply(quantity);
    }
}
