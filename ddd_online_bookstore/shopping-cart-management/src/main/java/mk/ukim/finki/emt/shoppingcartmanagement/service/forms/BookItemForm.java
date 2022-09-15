package mk.ukim.finki.emt.shoppingcartmanagement.service.forms;

import lombok.Data;
import mk.ukim.finki.emt.shoppingcartmanagement.domain.valueobjects.Book;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class BookItemForm {

    @NotNull
    private Book book;

    @Min(1)
    private int quantity = 1;
}
