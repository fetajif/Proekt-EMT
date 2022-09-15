package mk.ukim.finki.emt.bookcatalog.services.form;

import lombok.Data;
import mk.ukim.finki.emt.bookcatalog.domain.valueobjects.AuthorId;
import mk.ukim.finki.emt.bookcatalog.domain.models.Category;
import mk.ukim.finki.emt.sharedkernel.financial.Money;


@Data
public class BookForm {

    private String isbn;

    private String title;

    private Category category;

    private int yearPublished;

    // dali treba ova ili ne?
    private AuthorId authorId;

    private Money price;

    private int inStock = 0;

}
