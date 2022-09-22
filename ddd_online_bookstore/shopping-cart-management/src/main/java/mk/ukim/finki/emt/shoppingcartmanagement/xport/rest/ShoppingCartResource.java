package mk.ukim.finki.emt.shoppingcartmanagement.xport.rest;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.shoppingcartmanagement.domain.exceptions.ShoppingCartIdNotExistException;
import mk.ukim.finki.emt.shoppingcartmanagement.domain.models.BookItem;
import mk.ukim.finki.emt.shoppingcartmanagement.domain.models.BookItemId;
import mk.ukim.finki.emt.shoppingcartmanagement.domain.models.ShoppingCart;
import mk.ukim.finki.emt.shoppingcartmanagement.domain.models.ShoppingCartId;
import mk.ukim.finki.emt.shoppingcartmanagement.domain.repository.ShoppingCartRepository;
import mk.ukim.finki.emt.shoppingcartmanagement.service.ShoppingCartService;
import mk.ukim.finki.emt.shoppingcartmanagement.service.forms.BookItemForm;
import mk.ukim.finki.emt.shoppingcartmanagement.service.forms.ShoppingCartForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/shopping-cart")
@AllArgsConstructor
public class ShoppingCartResource {

    private final ShoppingCartService shoppingCartService;

    @GetMapping
    public List<ShoppingCart> findAll() {
        return shoppingCartService.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<ShoppingCart> findById(@PathVariable ShoppingCartId id) {
        ShoppingCart c = this.shoppingCartService.findById(id)
                .orElseThrow(ShoppingCartIdNotExistException::new);

        if (c == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(c);
    }

    @GetMapping("/bookItems/{id}")
    public List<BookItem> findAllItemsOfShoppingCart(@PathVariable ShoppingCartId id) {
        ShoppingCart shoppingCart = shoppingCartService.findById(id)
                .orElseThrow(ShoppingCartIdNotExistException::new);

        return new ArrayList<>(shoppingCart.getBookItemList());
    }

    @PostMapping("/orderNow")
    public ResponseEntity<ShoppingCart> orderNow(@RequestBody ShoppingCartForm shoppingCartForm) {
        ShoppingCartId cId = this.shoppingCartService.orderNow(shoppingCartForm);
        if (cId == null) {
            return ResponseEntity.notFound().build();
        }
        ShoppingCart cart = shoppingCartService.findById(cId)
                .orElseThrow(ShoppingCartIdNotExistException::new);
        return ResponseEntity.ok().body(cart);
    }

    @PostMapping("/{id}/addItem")
    public ResponseEntity addItem(@PathVariable ShoppingCartId id, @RequestBody BookItemForm itemForm) {
        ShoppingCart cart = shoppingCartService.findById(id)
                .orElseThrow(ShoppingCartIdNotExistException::new);

        shoppingCartService.addItem(id, itemForm);

        ShoppingCart cart_after = shoppingCartService.findById(id)
                .orElseThrow(ShoppingCartIdNotExistException::new);

        if (cart.getBookItemList().size() == cart_after.getBookItemList().size()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/deleteItem")
    public ResponseEntity deleteItem(@PathVariable ShoppingCartId id, @RequestParam BookItemId bookItemId) {
        shoppingCartService.deleteItem(id, bookItemId);

        ShoppingCart cart = shoppingCartService.findById(id)
                .orElseThrow(ShoppingCartIdNotExistException::new);

        if (cart.getBookItemList().stream().anyMatch(item -> item.getId().equals(bookItemId))) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

}
