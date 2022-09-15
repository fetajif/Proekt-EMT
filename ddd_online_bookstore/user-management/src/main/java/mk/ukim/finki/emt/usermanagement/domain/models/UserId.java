package mk.ukim.finki.emt.usermanagement.domain.models;

import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.base.DomainObjectId;

public class UserId extends DomainObjectId {

    private UserId() {
        super(UserId.randomId(UserId.class).getId());
    }

    public UserId(@NonNull String uuid) {
        super(uuid);
    }

    public static UserId of(String uuid) {
        return new UserId(uuid);
    }

}
