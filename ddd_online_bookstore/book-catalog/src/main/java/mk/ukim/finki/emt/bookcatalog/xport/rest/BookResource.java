package mk.ukim.finki.emt.bookcatalog.xport.rest;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.bookcatalog.domain.models.Book;
import mk.ukim.finki.emt.bookcatalog.domain.models.BookId;
import mk.ukim.finki.emt.bookcatalog.services.BookService;
import mk.ukim.finki.emt.bookcatalog.services.form.BookForm;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/book")
@AllArgsConstructor
public class BookResource {

    private final BookService bookService;

    @GetMapping
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Book> findById(@PathVariable BookId id) {
        Book b = this.bookService.findById(id);

        if (b == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(b);
    }

    @PostMapping("/add")
    public ResponseEntity<Book> save(@RequestBody BookForm bookForm) {
        Book b = this.bookService.createBook(bookForm);
        if (b == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(b);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> save(@PathVariable BookId id, @RequestBody BookForm bookForm) {
        Book b = this.bookService.editBook(id, bookForm);
        if (b == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(b);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable BookId id) {
        this.bookService.deleteById(id);
        //if (this.bookService.findById(id) == null) {
            return ResponseEntity.ok().build();
        //}
        //return ResponseEntity.badRequest().build();
    }
}
