package mk.ukim.finki.emt.shoppingcartmanagement.domain.repository;

import mk.ukim.finki.emt.shoppingcartmanagement.domain.models.ShoppingCart;
import mk.ukim.finki.emt.shoppingcartmanagement.domain.models.ShoppingCartId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, ShoppingCartId> {

}
