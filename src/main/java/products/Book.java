package products;


public class Book extends Product {
    public Book(String name, double price, double weight) {
        super(name, price, weight);
        this.setCategory("Book");
    }

    public Book() {
        super();
    }
}
