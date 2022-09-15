package mk.ukim.finki.emt.usermanagement.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.usermanagement.domain.exceptions.UserNotFoundException;
import mk.ukim.finki.emt.usermanagement.domain.exceptions.UsernameNotValidException;
import mk.ukim.finki.emt.usermanagement.domain.models.User;
import mk.ukim.finki.emt.usermanagement.domain.models.UserId;
import mk.ukim.finki.emt.usermanagement.domain.repository.UserRepository;
import mk.ukim.finki.emt.usermanagement.service.UserService;
import mk.ukim.finki.emt.usermanagement.service.form.UserForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private static final String SPECIAL_CHARACTERS = ":;/?$&@#!*^%";

    @Override
    public User findById(UserId userId) {
        return userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User createUser(UserForm userForm) {

        if (userForm.getUsername().contains(SPECIAL_CHARACTERS)) {
            throw new UsernameNotValidException();
        }

        User user = User.build(userForm.getName(), userForm.getSurname(), userForm.getUsername(), userForm.getPassword(), userForm.getNumShoppingCarts());
        userRepository.save(user);
        return user;
    }

    @Override
    public User shoppingCartAdded(UserId userId, int numCarts) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        user.addCart(numCarts);
        userRepository.saveAndFlush(user);
        return user;
    }

    @Override
    public User shoppingCartRemoved(UserId userId, int numCarts) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        user.removeCart(numCarts);
        userRepository.saveAndFlush(user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
