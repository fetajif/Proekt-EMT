package mk.ukim.finki.emt.bookcatalog.xport.events;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.bookcatalog.domain.models.BookId;
import mk.ukim.finki.emt.bookcatalog.services.BookService;
import mk.ukim.finki.emt.sharedkernel.config.TopicHolder;
import mk.ukim.finki.emt.sharedkernel.events.DomainEvent;
import mk.ukim.finki.emt.sharedkernel.events.items.BookItemCreated;
import mk.ukim.finki.emt.sharedkernel.events.items.BookItemRemoved;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookEventListener {

    private final BookService bookService;

    @KafkaListener(topics = TopicHolder.TOPIC_BOOK_ITEM_CREATED, groupId = "bookCatalog")
    public void consumeBookItemCreatedEvent(String jsonMessage) {
        try {
            BookItemCreated event = DomainEvent.fromJson(jsonMessage, BookItemCreated.class);
            bookService.bookItemCreated(BookId.of(event.getBookId()), event.getQuantity());
        } catch (Exception e) {

        }
    }

    @KafkaListener(topics = TopicHolder.TOPIC_BOOK_ITEM_REMOVED, groupId = "bookCatalog")
    public void consumeBookItemRemovedEvent(String jsonMessage) {
        try {
            BookItemRemoved event = DomainEvent.fromJson(jsonMessage, BookItemRemoved.class);
            bookService.bookItemRemoved(BookId.of(event.getBookId()), event.getQuantity());
        } catch (Exception e) {

        }
    }
}
