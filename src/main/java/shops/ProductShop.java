package shops;

import myException.MaxSizeException;
import myException.NoSuchProductException;
import products.*;
import searchIndex.Search;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProductShop implements BookShop, CakeShop, SoapShop, Serializable {
    private List<Product> products;
    private int maxSize;
    private Search search;

    public ProductShop(int maxSize) {
        this.maxSize = maxSize;
        products = new ArrayList<>(maxSize);
        search = new Search();
    }

    public List<Product> getProducts() {
        return products;
    }

    public Search getSearch() {
        return search;
    }

    public void addProduct(Product product) throws MaxSizeException {
        if (products.size() < maxSize) {
            products.add(product);
        } else {
            throw new MaxSizeException();
        }
        search.add(product);
    }

    public void deleteProduct(String name) throws NoSuchProductException {
        System.out.println("Удаление продукта по совпадению в названии : " + name);
        products.removeIf(product -> product.getName().equalsIgnoreCase(name));
        search.delete(name);
    }

    public void deleteProduct(Predicate<String> predicate) {
        products.removeIf(product -> predicate.test(product.getName()));
        search.delete(predicate);
    }

    public int productCount() {
        return products.size();
    }

    public List<Product> belowPrice(double price) {
        System.out.println("Продукты с ценой ниже " + price);
        List<Product> result = new ArrayList<>();
        for (Product prod : products) {
            if (prod.getPrice() < price) {
                result.add(prod);
            }
        }
        return result;
    }

    public List<Product> findProductByName(String key) {
        System.out.println("Продукты содержащие '" + key + "'");
        List<Product> result = search.findByWorld(key);
        if (result == null) {
            throw new NoSuchProductException();
        }
        return result;
    }

    public List<Product> find(Predicate<String> predicate) {
        return new ArrayList<>(search.find(predicate));
    }

    public List<ShelfLifeProduct> shelfLifeMore(int shelfLife) {
        System.out.println("Продукты со сроком выше " + shelfLife);
        return products.stream().filter(p -> p instanceof ShelfLifeProduct).map(pd -> (ShelfLifeProduct) pd).collect(Collectors.toList());
    }

    public List<Book> getAllBook(){
        System.out.println("Получение списка книг");
        return products.stream().filter(p -> p.getCategory().equals("Book")).map(b -> (Book) b).collect(Collectors.toList());
    }

    public List<Soap> getAllSoap(){
        System.out.println("Получение списка мыл");
        return products.stream().filter(p -> p.getCategory().equals("Soap")).map(b -> (Soap) b).collect(Collectors.toList());
    }

    public List<Cake> getAllCake(){
        System.out.println("Получение списка тортов");
        return products.stream().filter(p -> p.getCategory().equals("Cake")).map(b -> (Cake) b).collect(Collectors.toList());
    }

    public void save(String fileName) {
        System.out.println("Сохранение магазина");
        File file = new File(fileName);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(this);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static ProductShop loadFromFile(String fileName) {
        System.out.println("Загрузка магазина");
        File file = new File(fileName);
        ProductShop loadedShop = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            loadedShop = (ProductShop) ois.readObject();
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        return loadedShop;
    }

    @Override
    public String toString() {
        return "Store{" +
                "products=" + products +
                ", maxCapacity=" + maxSize +
                '}';
    }
}
