package mk.ukim.finki.emt.shoppingcartmanagement.service.forms;

import lombok.Data;
import mk.ukim.finki.emt.sharedkernel.financial.Currency;
import mk.ukim.finki.emt.shoppingcartmanagement.domain.valueobjects.UserId;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class ShoppingCartForm {
    @NotNull
    private Currency currency;

    @Valid
    @NotEmpty
    private List<BookItemForm> items = new ArrayList<>();
}
