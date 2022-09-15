package mk.ukim.finki.emt.bookcatalog.domain.valueobjects;

import mk.ukim.finki.emt.sharedkernel.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class AuthorId extends DomainObjectId {
    protected AuthorId() {
        super(AuthorId.randomId(AuthorId.class).getId());
    }

    public AuthorId(String uuid) {
        super(uuid);
    }
}
