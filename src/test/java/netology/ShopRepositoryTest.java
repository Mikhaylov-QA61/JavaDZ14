package netology;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class ShopRepositoryTest {

    ShopRepository shop = new ShopRepository();
    Product product1 = new Product(1, "Хлеб", 10);
    Product product2 = new Product(22, "Молоко", 60);
    Product product3 = new Product(333, "Водка", 666);

    public void setup() {
        shop.add(product1);
        shop.add(product2);
        shop.add(product3);
    }

    @Test
    public void removeNullIdTest() {
        setup();

        Assertions.assertThrows(NotFoundException.class, () -> {
            shop.remove(555);
        });
    }

    @Test
    public void removeProductByIDTest() {
        setup();

        shop.remove(22);
        Product[] expected = {product1, product3};
        Product[] actual = shop.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void AddProductTest() {
        shop.add(product1);
        shop.add(product3);

        Product[] expected = {product1, product3};
        Product[] actual = shop.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void AddExistingProductTest() {
        shop.add(product1);
        shop.add(product2);
        shop.add(product3);

        Assertions.assertThrows(AlreadyExistsException.class, () -> shop.add(product2));
    }
}
