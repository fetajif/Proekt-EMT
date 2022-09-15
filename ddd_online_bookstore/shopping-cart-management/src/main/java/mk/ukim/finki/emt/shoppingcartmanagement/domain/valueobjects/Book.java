package mk.ukim.finki.emt.shoppingcartmanagement.domain.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.base.ValueObject;
import mk.ukim.finki.emt.sharedkernel.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.financial.Money;

@Getter
public class Book implements ValueObject {

    private final BookId id;
    private final String isbn;
    private final String title;
    private final Category category;
    private final int yearPublished;
    private final AuthorId authorId;
    private final Money price;
    private final int inStock;

    private Book() {
        this.id = BookId.randomId(BookId.class);
        this.isbn = "";
        this.title = "";
        this.category = null;
        this.yearPublished = 0;
        this.authorId = AuthorId.randomId(AuthorId.class);
        this.price = Money.valueOf(Currency.MKD, 0);
        this.inStock = 0;
    }

    @JsonCreator
    public Book(@JsonProperty("id") BookId id,
                @JsonProperty("isbn") String isbn,
                @JsonProperty("title") String title,
                @JsonProperty("category") Category category,
                @JsonProperty("yearPublished") int yearPublished,
                @JsonProperty("authorId") AuthorId authorId,
                @JsonProperty("price") Money price,
                @JsonProperty("inStock") int inStock) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.category = category;
        this.yearPublished = yearPublished;
        this.authorId = authorId;
        this.price = price;
        this.inStock = inStock;
    }
}
