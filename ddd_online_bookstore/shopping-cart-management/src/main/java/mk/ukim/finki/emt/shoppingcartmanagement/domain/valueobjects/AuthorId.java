package mk.ukim.finki.emt.shoppingcartmanagement.domain.valueobjects;

import mk.ukim.finki.emt.sharedkernel.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class AuthorId extends DomainObjectId {

    private AuthorId() {
        super(AuthorId.randomId(AuthorId.class).getId());
    }

    public AuthorId(String uuid) {
        super(uuid);
    }
}
