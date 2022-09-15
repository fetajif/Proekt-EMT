package mk.ukim.finki.emt.authorcatalog.domain.models;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.base.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "authors")
@Getter
public class Author extends AbstractEntity<AuthorId> {

    private String Name;
    private String Surname;
    private String Address;
    private String Url;

    private Author() {
        super(AuthorId.randomId(AuthorId.class));
    }

    public Author(String name, String surname, String address, String url) {
        super(AuthorId.randomId(AuthorId.class));
        this.Name = name;
        this.Surname = surname;
        this.Address = address;
        this.Url = url;
    }

    public static Author build(String name, String surname, String address, String url) {
        Author author = new Author();
        author.Name = name;
        author.Surname = surname;
        author.Address = address;
        author.Url = url;
        return author;
    }
}
