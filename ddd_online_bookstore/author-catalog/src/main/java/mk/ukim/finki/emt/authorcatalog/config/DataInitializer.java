package mk.ukim.finki.emt.authorcatalog.config;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.authorcatalog.domain.models.Author;
import mk.ukim.finki.emt.authorcatalog.domain.repository.AuthorRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
@AllArgsConstructor
public class DataInitializer {

    private final AuthorRepository authorRepository;

    @PostConstruct
    public void initData() {
        Author a1 = Author.build("Jane", "Austen", "Winchester, UK", "");
        Author a2 = Author.build("F. Scott", "Fitzgerald", "Los Angeles, California USA", "");
        if (authorRepository.findAll().isEmpty()) {
            authorRepository.saveAll(Arrays.asList(a1, a2));
        }

    }
}
