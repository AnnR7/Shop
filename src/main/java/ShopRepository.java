public class ShopRepository {
    private Product[] products = new Product[0];
    private ShopRepository repo;

    /**
     * Метод добавления товара в репозиторий
     * @param product — добавляемый товар
     */
    public void add(Product product) {


        Product addingProducts = findById(product.getId());
        if (addingProducts != null) {
            throw new AlreadyExistsException(
                    "Товар с id: " + product.getId() + " уже существует"
            );
        }

        products = addToArray(products, product);
    }

    /**
     * Вспомогательный метод для имитации добавления элемента в массив
     * @param current — массив, в который мы хотим добавить элемент``
     * @param product — элемент, который мы хотим добавить
     * @return — возвращает новый массив, который выглядит, как тот, что мы передали,
     * но с добавлением нового элемента в конец
     */
    private Product[] addToArray(Product[] current, Product product) {  // Создаем массив на единицу длиннее
        Product[] tmp = new Product[current.length + 1];
        for (int i = 0; i < current.length; i++) {
            tmp[i] = current[i];
        }
        tmp[tmp.length - 1] = product;
        return tmp;

       }


    public Product[] findAll() {
        return products;
    }

    // Этот способ мы рассматривали в теории в теме про композицию
    public void remove(int id) {

        Product removingProduct = findById(id);
        if (removingProduct == null) {
            throw new NotFoundException(
                    "Товар с id: " + id + " не найден"
            );
        }
        
        Product[] tmp = new Product[products.length - 1];    // Создаем массив на единицу короче
        int copyToIndex = 0;                              // Переменная, отвечающая за индекс
        for (Product product : products) {
            if (product.getId() != id) {         // Перебираем все продукты
                tmp[copyToIndex] = product;      // Если id не совпадает с исключаемым, то товар копируется
                copyToIndex++;                   // индекс увеличится
            }                                    // Если id совпадает с исключаемым, то ничего не происходит
        }
        products = tmp;
    }

    private Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id){
                return product;
            }
        }
        return null;
    }
}
