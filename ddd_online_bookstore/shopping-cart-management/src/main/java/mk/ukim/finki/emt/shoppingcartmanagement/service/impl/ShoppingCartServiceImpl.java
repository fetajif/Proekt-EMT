package mk.ukim.finki.emt.shoppingcartmanagement.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.sharedkernel.events.items.BookItemCreated;
import mk.ukim.finki.emt.sharedkernel.events.items.BookItemRemoved;
import mk.ukim.finki.emt.sharedkernel.infra.DomainEventPublisher;
import mk.ukim.finki.emt.shoppingcartmanagement.domain.exceptions.BookItemIdNotExistException;
import mk.ukim.finki.emt.shoppingcartmanagement.domain.exceptions.ShoppingCartIdNotExistException;
import mk.ukim.finki.emt.shoppingcartmanagement.domain.models.BookItem;
import mk.ukim.finki.emt.shoppingcartmanagement.domain.models.BookItemId;
import mk.ukim.finki.emt.shoppingcartmanagement.domain.models.ShoppingCart;
import mk.ukim.finki.emt.shoppingcartmanagement.domain.models.ShoppingCartId;
import mk.ukim.finki.emt.shoppingcartmanagement.domain.repository.ShoppingCartRepository;
import mk.ukim.finki.emt.shoppingcartmanagement.domain.valueobjects.UserId;
import mk.ukim.finki.emt.shoppingcartmanagement.service.ShoppingCartService;
import mk.ukim.finki.emt.shoppingcartmanagement.service.forms.BookItemForm;
import mk.ukim.finki.emt.shoppingcartmanagement.service.forms.ShoppingCartForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final DomainEventPublisher domainEventPublisher;
    private final Validator validator;

    @Override
    public ShoppingCartId orderNow(ShoppingCartForm shoppingCartForm) {
        Objects.requireNonNull(shoppingCartForm, "ShoppingCart must not be null");
        var constraintViolations = validator.validate(shoppingCartForm);
        if (constraintViolations.size() > 0) {
            throw new ConstraintViolationException("The shopping-cart form is not valid", constraintViolations);
        }
        var newShoppingCart = shoppingCartRepository.saveAndFlush(toDomainObject(shoppingCartForm));
        newShoppingCart.getBookItemList().forEach(item -> domainEventPublisher
                .publish(new BookItemCreated(item.getBookId().getId(), item.getQuantity())));
        return newShoppingCart.getId();
    }

    @Override
    public List<ShoppingCart> findAll() {
        return shoppingCartRepository.findAll();
    }

    @Override
    public Optional<ShoppingCart> findById(ShoppingCartId shoppingCartId) {
        return shoppingCartRepository.findById(shoppingCartId);
    }

    @Override
    public void addItem(ShoppingCartId shoppingCartId, BookItemForm itemForm) throws ShoppingCartIdNotExistException {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(shoppingCartId)
                .orElseThrow(ShoppingCartIdNotExistException::new);

        shoppingCart.addItem(itemForm.getBook(), itemForm.getQuantity());
        shoppingCartRepository.saveAndFlush(shoppingCart);

        domainEventPublisher.publish(new BookItemCreated(itemForm.getBook().getId().getId(), itemForm.getQuantity()));
    }

    @Override
    public void deleteItem(ShoppingCartId shoppingCartId, BookItemId bookItemId) throws ShoppingCartIdNotExistException, BookItemIdNotExistException {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(shoppingCartId)
                .orElseThrow(ShoppingCartIdNotExistException::new);

        BookItem bookItem = shoppingCart.getBookItemList().stream().filter(item -> item.getId().equals(bookItemId)).findFirst().get();

        shoppingCart.removeItem(bookItemId);
        shoppingCartRepository.saveAndFlush(shoppingCart);

        domainEventPublisher.publish(new BookItemRemoved(bookItem.getId().getId(), bookItem.getQuantity()));
    }

    private ShoppingCart toDomainObject(ShoppingCartForm shoppingCartForm) {
        ShoppingCart shoppingCart = new ShoppingCart(LocalDateTime.now(), shoppingCartForm.getCurrency());
        shoppingCartForm.getItems().forEach(item -> shoppingCart.addItem(item.getBook(), item.getQuantity()));
        return shoppingCart;
    }
}
