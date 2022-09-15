package mk.ukim.finki.emt.shoppingcartmanagement.domain.models;

import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.base.DomainObjectId;

public class ShoppingCartId extends DomainObjectId {

    private ShoppingCartId() {
        super(ShoppingCartId.randomId(ShoppingCartId.class).getId());
    }

    public ShoppingCartId(@NonNull String uuid) {
        super(uuid);
    }
}
