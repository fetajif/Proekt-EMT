package mk.ukim.finki.emt.shoppingcartmanagement.domain.models;

import mk.ukim.finki.emt.sharedkernel.base.DomainObjectId;

public class BookItemId extends DomainObjectId {

    private BookItemId() {
        super(BookItemId.randomId(BookItemId.class).getId());
    }

    public BookItemId(String uuid) {
        super(uuid);
    }
}
