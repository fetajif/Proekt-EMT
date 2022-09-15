package mk.ukim.finki.emt.usermanagement.domain.valueobjects;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.base.ValueObject;
import mk.ukim.finki.emt.sharedkernel.financial.Currency;

import java.time.LocalDateTime;


@Getter
public class ShoppingCart implements ValueObject {

    private LocalDateTime dateCreated;

    private Currency currency;
}
