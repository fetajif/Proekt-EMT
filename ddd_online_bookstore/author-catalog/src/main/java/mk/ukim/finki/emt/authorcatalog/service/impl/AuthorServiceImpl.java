package mk.ukim.finki.emt.authorcatalog.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.authorcatalog.domain.exceptions.AuthorNotFoundException;
import mk.ukim.finki.emt.authorcatalog.domain.models.Author;
import mk.ukim.finki.emt.authorcatalog.domain.models.AuthorId;
import mk.ukim.finki.emt.authorcatalog.domain.repository.AuthorRepository;
import mk.ukim.finki.emt.authorcatalog.service.AuthorService;
import mk.ukim.finki.emt.authorcatalog.service.form.AuthorForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public Author findById(AuthorId authorId) {
        return authorRepository.findById(authorId)
                .orElseThrow(AuthorNotFoundException::new);
    }

    @Override
    public Author createAuthor(AuthorForm authorForm) {
        Author a = Author.build(authorForm.getName(), authorForm.getSurname(), authorForm.getAddress(), authorForm.getUrl());
        authorRepository.save(a);
        return a;
    }

    @Override
    public Author editAuthor(AuthorId authorId, AuthorForm authorForm) {
        this.deleteById(authorId);
        return this.createAuthor(authorForm);
    }

    @Override
    public void deleteById(AuthorId authorId) {
        authorRepository.deleteById(authorId);
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }
}
