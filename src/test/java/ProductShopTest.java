import myException.MaxSizeException;
import org.junit.jupiter.api.Test;
import products.Book;
import products.Cake;
import products.Soap;
import shops.ProductShop;

import static org.junit.jupiter.api.Assertions.*;

public class ProductShopTest {

    @Test
    public void shouldAddThreeProduct() {
        ProductShop shop = new ProductShop(3);
        shop.addProduct(new Soap("soap1", 3, 10));
        shop.addProduct(new Book("book1", 10, 100));
        shop.addProduct(new Cake("cake1", 8, 700, 4));

        assertTrue(shop.getProducts().size() == 3);
    }

    @Test()
    public void shouldThrowException() {
        ProductShop shop = new ProductShop(3);
        assertThrows(MaxSizeException.class, () -> {
            shop.addProduct(new Soap("soap1", 3, 10));
            shop.addProduct(new Book("book1", 10, 100));
            shop.addProduct(new Cake("cake1", 8, 700, 4));
            shop.addProduct(new Cake("cake2", 3, 44, 2));
            shop.addProduct(new Cake("cake2", 3, 44, 2));
            shop.addProduct(new Cake("cake2", 3, 44, 2));
        });
    }
}
