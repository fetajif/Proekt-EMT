package mk.ukim.finki.emt.shoppingcartmanagement.domain.valueobjects;

import mk.ukim.finki.emt.sharedkernel.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class UserId extends DomainObjectId {

    private UserId() {
        super(BookId.randomId(BookId.class).getId());
    }

    public UserId(String uuid) {
        super(uuid);
    }
}
