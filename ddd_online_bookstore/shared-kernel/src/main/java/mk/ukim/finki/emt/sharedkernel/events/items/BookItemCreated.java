package mk.ukim.finki.emt.sharedkernel.events.items;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.config.TopicHolder;
import mk.ukim.finki.emt.sharedkernel.events.DomainEvent;

@Getter
public class BookItemCreated extends DomainEvent {

    private String bookId;
    private int quantity;

    public BookItemCreated(String topic) {
        super(TopicHolder.TOPIC_BOOK_ITEM_CREATED);
    }

    public BookItemCreated(String bookId, int quantity) {
        super(TopicHolder.TOPIC_BOOK_ITEM_CREATED);
        this.bookId = bookId;
        this.quantity = quantity;
    }
}
