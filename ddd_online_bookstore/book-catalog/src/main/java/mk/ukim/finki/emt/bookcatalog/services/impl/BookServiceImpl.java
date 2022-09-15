package mk.ukim.finki.emt.bookcatalog.services.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.bookcatalog.domain.exceptions.BookIsbnNotValid;
import mk.ukim.finki.emt.bookcatalog.domain.exceptions.BookNotFoundException;
import mk.ukim.finki.emt.bookcatalog.domain.models.Book;
import mk.ukim.finki.emt.bookcatalog.domain.models.BookId;
import mk.ukim.finki.emt.bookcatalog.domain.repository.BookRepository;
import mk.ukim.finki.emt.bookcatalog.services.BookService;
import mk.ukim.finki.emt.bookcatalog.services.form.BookForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Book findById(BookId bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(BookNotFoundException::new);
    }

    @Override
    public Book createBook(BookForm bookForm) {

        if (bookForm.getIsbn().length() != 13) {
            throw new BookIsbnNotValid();
        }

        Book b = Book.build(bookForm.getIsbn(), bookForm.getTitle(),
                bookForm.getCategory(), bookForm.getYearPublished(),
                bookForm.getAuthorId(), bookForm.getPrice(), bookForm.getInStock());
        bookRepository.save(b);
        return b;
    }

    @Override
    public Book bookItemCreated(BookId bookId, int quantity) {
        Book b = bookRepository.findById(bookId)
                .orElseThrow(BookNotFoundException::new);
        b.sellBook(quantity);
        bookRepository.saveAndFlush(b);
        return b;
    }

    @Override
    public Book bookItemRemoved(BookId bookId, int quantity) {
        Book b = bookRepository.findById(bookId)
                .orElseThrow(BookNotFoundException::new);
        b.returnBook(quantity);
        bookRepository.saveAndFlush(b);
        return b;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}
