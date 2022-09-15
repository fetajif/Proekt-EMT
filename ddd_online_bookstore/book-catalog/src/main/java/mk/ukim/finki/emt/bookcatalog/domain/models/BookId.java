package mk.ukim.finki.emt.bookcatalog.domain.models;

import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.base.DomainObjectId;

public class BookId extends DomainObjectId {

    private BookId() {
        super(BookId.randomId(BookId.class).getId());
    }

    public BookId(@NonNull String uuid) {
        super(uuid);
    }

    public static BookId of(String uuid) {
        return new BookId(uuid);
    }
}
