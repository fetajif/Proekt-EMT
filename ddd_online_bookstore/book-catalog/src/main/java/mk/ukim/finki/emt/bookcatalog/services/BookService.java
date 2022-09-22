package mk.ukim.finki.emt.bookcatalog.services;

import mk.ukim.finki.emt.bookcatalog.domain.models.Book;
import mk.ukim.finki.emt.bookcatalog.domain.models.BookId;
import mk.ukim.finki.emt.bookcatalog.services.form.BookForm;

import java.util.List;

public interface BookService {
    Book findById(BookId bookId);
    Book createBook(BookForm bookForm);
    Book editBook(BookId bookId, BookForm bookForm);
    void deleteById(BookId bookId);
    Book bookItemCreated(BookId bookId, int quantity);
    Book bookItemRemoved(BookId bookId, int quantity);
    List<Book> findAll();
}
