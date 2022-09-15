package mk.ukim.finki.emt.usermanagement.service.form;

import lombok.Data;

@Data
public class UserForm {

    private String name;
    private String surname;
    private String username;
    private String password;
    private int numShoppingCarts = 0;
}
