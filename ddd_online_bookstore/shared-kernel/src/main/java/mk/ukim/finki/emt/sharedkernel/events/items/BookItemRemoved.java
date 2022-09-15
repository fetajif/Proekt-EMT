package mk.ukim.finki.emt.sharedkernel.events.items;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.config.TopicHolder;
import mk.ukim.finki.emt.sharedkernel.events.DomainEvent;

@Getter
public class BookItemRemoved extends DomainEvent {

    private String bookId;
    private int quantity;

    public BookItemRemoved(String topic) {
        super(TopicHolder.TOPIC_BOOK_ITEM_REMOVED);
    }

    public BookItemRemoved(String bookId, int quantity) {
        super(TopicHolder.TOPIC_BOOK_ITEM_REMOVED);
        this.bookId = bookId;
        this.quantity = quantity;
    }
}
