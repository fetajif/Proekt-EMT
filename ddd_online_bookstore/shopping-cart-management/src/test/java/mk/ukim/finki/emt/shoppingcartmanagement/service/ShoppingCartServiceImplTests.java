package mk.ukim.finki.emt.shoppingcartmanagement.service;

import mk.ukim.finki.emt.sharedkernel.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.financial.Money;
import mk.ukim.finki.emt.shoppingcartmanagement.domain.exceptions.ShoppingCartIdNotExistException;
import mk.ukim.finki.emt.shoppingcartmanagement.domain.models.ShoppingCart;
import mk.ukim.finki.emt.shoppingcartmanagement.domain.models.ShoppingCartId;
import mk.ukim.finki.emt.shoppingcartmanagement.domain.valueobjects.AuthorId;
import mk.ukim.finki.emt.shoppingcartmanagement.domain.valueobjects.Book;
import mk.ukim.finki.emt.shoppingcartmanagement.domain.valueobjects.BookId;
import mk.ukim.finki.emt.shoppingcartmanagement.domain.valueobjects.Category;
import mk.ukim.finki.emt.shoppingcartmanagement.service.forms.BookItemForm;
import mk.ukim.finki.emt.shoppingcartmanagement.service.forms.ShoppingCartForm;
import mk.ukim.finki.emt.shoppingcartmanagement.xport.client.BookClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.JavaVersion;
import org.springframework.boot.system.SystemProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.SpringVersion;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ShoppingCartServiceImplTests {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private BookClient bookClient;

    private static Book newBook(String isbn, String title, Money price) {
        return new Book(BookId.randomId(BookId.class), isbn, title, Category.FANTASY,
                0, AuthorId.randomId(AuthorId.class), price, 0);
    }

    @Test
    public void testOrderNow() {

        BookItemForm bi1 = new BookItemForm();
        bi1.setBook(newBook("9781785652509", "The Invisible Life of Addie LaRue", Money.valueOf(Currency.MKD, 680)));

        BookItemForm bi2 = new BookItemForm();
        bi2.setBook(newBook("9788416517275", "The Seven Husbands of Evelyn Hugo", Money.valueOf(Currency.MKD, 500)));

        ShoppingCartForm shoppingCartForm = new ShoppingCartForm();
        shoppingCartForm.setCurrency(Currency.MKD);
        shoppingCartForm.setItems(Arrays.asList(bi1, bi2));

        ShoppingCartId shoppingCartId = shoppingCartService.orderNow(shoppingCartForm);
        ShoppingCart newShoppingCart = shoppingCartService.findById(shoppingCartId)
                .orElseThrow(ShoppingCartIdNotExistException::new);

        Assertions.assertEquals(newShoppingCart.total(), Money.valueOf(Currency.MKD, 1180));
    }

    @Test
    public void testOrderNowWithRealData() {
        List<Book> bookList = bookClient.findAll();
        Book b1 = bookList.get(0);
        Book b2 = bookList.get(1);

        BookItemForm oi1 = new BookItemForm();
        oi1.setBook(b1);
        oi1.setQuantity(1);

        BookItemForm oi2 = new BookItemForm();
        oi2.setBook(b1);
        oi2.setQuantity(2);

        ShoppingCartForm shoppingCartForm = new ShoppingCartForm();
        shoppingCartForm.setCurrency(Currency.MKD);
        shoppingCartForm.setItems(Arrays.asList(oi1,oi2));

        ShoppingCartId newShoppingCartId = shoppingCartService.orderNow(shoppingCartForm);
        ShoppingCart newShoppingCart = shoppingCartService.findById(newShoppingCartId).orElseThrow(ShoppingCartIdNotExistException::new);

        Money outMoney = b1.getPrice().multiply(oi1.getQuantity()).add(b2.getPrice().multiply(oi2.getQuantity()));
        Assertions.assertEquals(newShoppingCart.total(),outMoney);
    }

    @Test
    public void test123() {
        System.out.println("Spring Version: " + SpringVersion.getVersion());
        System.out.println("JDK Version: " + SystemProperties.get("java.version"));
        System.out.println("Java Version: " + JavaVersion.getJavaVersion().toString());
    }
}
