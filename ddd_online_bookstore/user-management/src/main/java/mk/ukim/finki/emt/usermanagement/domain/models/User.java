package mk.ukim.finki.emt.usermanagement.domain.models;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.base.AbstractEntity;
import mk.ukim.finki.emt.usermanagement.domain.valueobjects.ShoppingCart;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book_users")
@Getter
public class User extends AbstractEntity<UserId> {

    private String name;
    private String surname;
    private String username;
    private String password;
    private int numShoppingCarts = 0;

    protected User() {
        super(UserId.randomId(UserId.class));
    }

    public User(String name, String surname, String username, String password, int numShoppingCarts) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.numShoppingCarts = numShoppingCarts;
    }

    public static User build(String name, String surname, String username, String password, int numShoppingCarts) {
        User u = new User();
        u.name = name;
        u.surname = surname;
        u.username = username;
        u.password = password;
        u.numShoppingCarts = numShoppingCarts;
        return u;
    }

    public void addCart(int carts) {
        this.numShoppingCarts += carts;
    }

    public void removeCart(int carts) {
        this.numShoppingCarts -= carts;
    }
}
