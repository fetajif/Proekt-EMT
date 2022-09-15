package mk.ukim.finki.emt.bookcatalog.config;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.bookcatalog.domain.models.Book;
import mk.ukim.finki.emt.bookcatalog.domain.repository.BookRepository;
import mk.ukim.finki.emt.bookcatalog.domain.valueobjects.AuthorId;
import mk.ukim.finki.emt.bookcatalog.domain.models.Category;
import mk.ukim.finki.emt.sharedkernel.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.financial.Money;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
@AllArgsConstructor
public class DataInitializer {

    private final BookRepository bookRepository;

    @PostConstruct
    public void initData() {
        AuthorId a1 = new AuthorId("c8542664-c6eb-4bac-b0d6-d4b8b8226dc8");
        AuthorId a2 = new AuthorId("9c8f6e96-e7e3-42e9-ae45-ad53cac555cc");
        Book b1 = new Book("9780141439518", "Pride and Prejudice", Category.ROMANCE,
                1813, a1, Money.valueOf(Currency.MKD, 300), 300);

        Book b2 = new Book("9780333791035", "The Great Gatsby", Category.TRAGEDY,
                1925, a2, Money.valueOf(Currency.MKD, 350), 400);

        if (bookRepository.findAll().isEmpty()) {
            bookRepository.saveAll(Arrays.asList(b1, b2));
        }
    }

}
