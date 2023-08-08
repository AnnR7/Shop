import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShopRepositoryTest {

    @Test
    void testRemoveWhenProductExist() {

        ShopRepository repo = new ShopRepository();

        Product product1 = new Product(1, "книга", 2_000);
        Product product2 = new Product(22, "футболка", 3_000);
        Product product3 = new Product(333, "кружка", 1_000);

        repo.add(product1);
        repo.add(product2);
        repo.add(product3);
        repo.remove(22);

        Product[] actual = repo.findAll();
        Product[] expected = {product1, product3};

        Assertions.assertArrayEquals(expected, actual);

    }
//    @Test
//    void removeWhenProductNotExist() {
//
//        ShopRepository repo = new ShopRepository();
//
//        Product product1 = new Product(1, "книга", 2_000);
//        Product product2 = new Product(22, "футболка", 3_000);
//        Product product3 = new Product(333, "кружка", 1_000);
//
//        repo.add(product1);
//        repo.add(product2);
//        repo.add(product3);
//        repo.remove(4444);
//
//        Product[] actual = repo.findAll();
//        Product[] expected = {product1, product2, product3};
//
//        Assertions.assertArrayEquals(expected, actual);
//    }
    @Test
    void testRemoveWhenProductNotExist() {

        ShopRepository repo = new ShopRepository();

        Product product1 = new Product(1, "книга", 2_000);
        Product product2 = new Product(22, "футболка", 3_000);
        Product product3 = new Product(333, "кружка", 1_000);

        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        Assertions.assertThrows(NotFoundException.class, () ->
            repo.remove(4444)
        );
    }
    @Test
    void addWhenProductNotExist() {

        ShopRepository repo = new ShopRepository();

        Product product1 = new Product(1, "книга", 2_000);
        Product product2 = new Product(22, "футболка", 3_000);
        Product product3 = new Product(333, "кружка", 1_000);
        Product product4 = new Product(4444,"машинка",10_000);

        repo.add(product1);
        repo.add(product2);
        repo.add(product3);
        repo.add(product4);

        Product[] actual = repo.findAll();
        Product[] expected = {product1, product2, product3, product4};

        Assertions.assertArrayEquals(expected, actual);

    }
    @Test
    void addWhenProductExist() {

        ShopRepository repo = new ShopRepository();

        Product product1 = new Product(1, "книга", 2_000);
        Product product2 = new Product(22, "футболка", 3_000);
        Product product3 = new Product(333, "кружка", 1_000);
        Product product4 = new Product(4444,"машинка",10_000);

        repo.add(product1);
        repo.add(product2);
        repo.add(product3);
        repo.add(product4);

        Assertions.assertThrows(AlreadyExistsException.class, () ->
                repo.add(new Product(333, "тапки", 200))
        );

    }
}