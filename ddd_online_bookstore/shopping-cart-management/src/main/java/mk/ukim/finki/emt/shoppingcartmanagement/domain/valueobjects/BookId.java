package mk.ukim.finki.emt.shoppingcartmanagement.domain.valueobjects;

import mk.ukim.finki.emt.sharedkernel.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class BookId extends DomainObjectId {

    private BookId() {
        super(BookId.randomId(BookId.class).getId());
    }

    public BookId(String uuid) {
        super(uuid);
    }
}
