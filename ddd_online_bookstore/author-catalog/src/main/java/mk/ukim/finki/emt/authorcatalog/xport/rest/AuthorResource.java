package mk.ukim.finki.emt.authorcatalog.xport.rest;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.authorcatalog.domain.models.Author;
import mk.ukim.finki.emt.authorcatalog.domain.models.AuthorId;
import mk.ukim.finki.emt.authorcatalog.domain.repository.AuthorRepository;
import mk.ukim.finki.emt.authorcatalog.service.AuthorService;
import mk.ukim.finki.emt.authorcatalog.service.form.AuthorForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/author")
@AllArgsConstructor
public class AuthorResource {

    private final AuthorService authorService;

    @GetMapping
    public List<Author> findAll() {
        return authorService.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Author> findById(@PathVariable AuthorId id) {
        Author a = this.authorService.findById(id);
        if (a == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(a);
    }

    @PostMapping("/add")
    public ResponseEntity<Author> save(@RequestBody AuthorForm authorForm) {
        Author a = this.authorService.createAuthor(authorForm);
        if (a == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(a);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Author> save(@PathVariable AuthorId id, @RequestBody AuthorForm authorForm) {
        Author a = this.authorService.editAuthor(id, authorForm);
        if (a == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(a);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable AuthorId id) {
        this.authorService.deleteById(id);
//        if (this.authorService.findById(id) == null) {
            return ResponseEntity.ok().build();
//        }
//        return ResponseEntity.badRequest().build();
    }
}
