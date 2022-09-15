package mk.ukim.finki.emt.bookcatalog.domain.models;

import lombok.Getter;
import mk.ukim.finki.emt.bookcatalog.domain.valueobjects.AuthorId;
import mk.ukim.finki.emt.sharedkernel.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.financial.Money;

import javax.persistence.*;

@Entity
@Table(name = "books")
@Getter
public class Book extends AbstractEntity<BookId> {

    //@Column(name = "isbn", unique = true, nullable = false)
    private String isbn;

    private String title;

//    @Enumerated(EnumType.STRING)
    private Category category;

    private int yearPublished;

    @AttributeOverride(name = "id", column = @Column(name = "author_id", nullable = false))
    private AuthorId authorId;

    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "price_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "price_currency"))
    })
    private Money price;

    private int inStock = 0;

    protected Book() {
        super(BookId.randomId(BookId.class));
    }

    public Book(String isbn, String title, Category category, int yearPublished, AuthorId authorId, Money price, int inStock) {
        super(BookId.randomId(BookId.class));
        this.isbn = isbn;
        this.title = title;
        this.category = category;
        this.yearPublished = yearPublished;
        this.authorId = authorId;
        this.price = price;
        this.inStock = inStock;
    }

    public static Book build(String isbn, String title, Category category, int yearPublished, AuthorId authorId, Money price, int inStock) {
        Book b = new Book();
        b.isbn = isbn;
        b.title = title;
        b.category = category;
        b.yearPublished = yearPublished;
        b.authorId = authorId;
        b.price = price;
        b.inStock = inStock;
        return b;
    }

    public void sellBook(int qty) {
        this.inStock -= qty;
    }

    public void returnBook(int qty) {
        this.inStock += qty;
    }
}
