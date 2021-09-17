import myException.MaxSizeException;
import myException.NoSuchProductException;
import products.Book;
import products.Cake;
import products.Product;
import products.Soap;
import searchIndex.Search;
import shops.ProductShop;

import java.util.List;
import java.util.stream.Collectors;


class Main {
    public static void main(String[] args) {
        ProductShop shop = new ProductShop(10);
        try {
            shop.addProduct(new Soap("soap1", 3, 10));
            shop.addProduct(new Book("book1", 10, 100));
            shop.addProduct(new Book("book1 + 1", 10, 100));
            shop.addProduct(new Cake("cake1", 8, 700, 4));
            shop.addProduct(new Book("book2", 15, 150));
            shop.addProduct(new Soap("soap2", 4, 13));
            shop.addProduct(new Cake("cake2", 5, 600, 3));
            shop.addProduct(new Soap("soap3", 3, 10));
            shop.addProduct(new Cake("cake3", 8, 700, 6));
            shop.addProduct(new Book("book3", 15, 150));
            shop.addProduct(new Soap("soap4", 4, 13));
            shop.addProduct(new Cake("cake4", 5, 600, 3));
        } catch (MaxSizeException e) {
            e.printStackTrace();
        }
        System.out.println(shop);
        System.out.println(shop.find(name -> name.equals("book1")));
        shop.deleteProduct(name -> name.startsWith("book"));
        System.out.println();
        System.out.println(shop);

        Product book = new Book("война и мир", 1, 1);
        Product book1 = new Book("война на дркгой планета", 1, 1);
        Product book2 = new Book("и грянул гром", 1, 1);
        Product book3 = new Book("мир труд май", 1, 1);


        Product book4 = new Book("тихий дон", 1, 1);
        Product book5 = new Book("тихий океан", 1, 1);
        Product book6 = new Book("океан", 1, 1);
        Product book7 = new Book("тихий океан и дно", 1, 1);
        Search searcher = new Search();

        searcher.add(book);
        searcher.add(book1);
        searcher.add(book2);
        searcher.add(book3);
        searcher.add(book4);
        searcher.add(book5);
        searcher.add(book6);
        searcher.add(book7);

        List<Product> collect = searcher.streamOfComp(book5).collect(Collectors.toList());
        System.out.println(collect);
    }
}