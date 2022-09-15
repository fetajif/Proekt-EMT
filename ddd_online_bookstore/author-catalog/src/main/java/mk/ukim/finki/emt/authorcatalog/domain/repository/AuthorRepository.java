package mk.ukim.finki.emt.authorcatalog.domain.repository;

import mk.ukim.finki.emt.authorcatalog.domain.models.Author;
import mk.ukim.finki.emt.authorcatalog.domain.models.AuthorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, AuthorId> {
}
