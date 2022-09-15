package mk.ukim.finki.emt.shoppingcartmanagement.service;

import mk.ukim.finki.emt.shoppingcartmanagement.domain.exceptions.BookItemIdNotExistException;
import mk.ukim.finki.emt.shoppingcartmanagement.domain.exceptions.ShoppingCartIdNotExistException;
import mk.ukim.finki.emt.shoppingcartmanagement.domain.models.BookItemId;
import mk.ukim.finki.emt.shoppingcartmanagement.domain.models.ShoppingCart;
import mk.ukim.finki.emt.shoppingcartmanagement.domain.models.ShoppingCartId;
import mk.ukim.finki.emt.shoppingcartmanagement.service.forms.BookItemForm;
import mk.ukim.finki.emt.shoppingcartmanagement.service.forms.ShoppingCartForm;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartService {
    ShoppingCartId orderNow(ShoppingCartForm shoppingCartForm);
    List<ShoppingCart> findAll();
    Optional<ShoppingCart> findById(ShoppingCartId shoppingCartId);
    void addItem(ShoppingCartId shoppingCartId, BookItemForm itemForm) throws ShoppingCartIdNotExistException;
    void deleteItem(ShoppingCartId shoppingCartId, BookItemId bookItemId) throws ShoppingCartIdNotExistException, BookItemIdNotExistException;
}
