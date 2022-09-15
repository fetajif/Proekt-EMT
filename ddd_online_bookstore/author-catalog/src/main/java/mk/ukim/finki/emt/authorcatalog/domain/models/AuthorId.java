package mk.ukim.finki.emt.authorcatalog.domain.models;

import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.base.DomainObjectId;

public class AuthorId extends DomainObjectId {
    private AuthorId() {
        super(AuthorId.randomId(AuthorId.class).getId());
    }

    public AuthorId(@NonNull String uuid) {
        super(uuid);
    }

    public static AuthorId of(String uuid) {
        return new AuthorId(uuid);
    }
}
