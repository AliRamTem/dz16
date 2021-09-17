package products;


public class Soap extends Product {
    public Soap(String name, double price, double weight) {
        super(name, price, weight);
        this.setCategory("Soap");
    }

    public Soap() {
        super();
    }
}
