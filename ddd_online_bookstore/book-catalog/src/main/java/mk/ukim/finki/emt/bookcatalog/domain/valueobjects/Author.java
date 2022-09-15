package mk.ukim.finki.emt.bookcatalog.domain.valueobjects;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.base.ValueObject;

@Getter
public class Author implements ValueObject {

    private final String Name;
    private final String Surname;
    private final String Address;
    private final String Url;
    private final AuthorId id;

    public Author(AuthorId id, String name, String surname, String address, String url) {
        this.id = id;
        this.Name = name;
        this.Surname = surname;
        this.Address = address;
        this.Url = url;
    }

//    public static Author build(String name, String surname, String address, String url) {
//        Author author = new Author();
//        author.Name = name;
//        author.Surname = surname;
//        author.Address = address;
//        author.Url = url;
//        return author;
//    }
}
