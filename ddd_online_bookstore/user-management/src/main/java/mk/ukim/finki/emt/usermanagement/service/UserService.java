package mk.ukim.finki.emt.usermanagement.service;

import mk.ukim.finki.emt.usermanagement.domain.models.User;
import mk.ukim.finki.emt.usermanagement.domain.models.UserId;
import mk.ukim.finki.emt.usermanagement.service.form.UserForm;

import java.util.List;

public interface UserService {

    User findById(UserId userId);
    User createUser(UserForm userForm);
    User shoppingCartAdded(UserId userId, int numCarts);
    User shoppingCartRemoved(UserId userId, int numCarts);
    List<User> findAll();

}
