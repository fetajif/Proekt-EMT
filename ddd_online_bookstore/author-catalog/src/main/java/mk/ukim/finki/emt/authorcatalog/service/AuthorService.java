package mk.ukim.finki.emt.authorcatalog.service;

import mk.ukim.finki.emt.authorcatalog.domain.models.Author;
import mk.ukim.finki.emt.authorcatalog.domain.models.AuthorId;
import mk.ukim.finki.emt.authorcatalog.service.form.AuthorForm;

import java.util.List;

public interface AuthorService {
    Author findById(AuthorId authorId);
    Author createAuthor(AuthorForm authorForm);
    void removeAuthor(AuthorId authorId);
    List<Author> findAll();
}
