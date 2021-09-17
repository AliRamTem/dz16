package products;


public class Cake extends Product implements ShelfLifeProduct {

    private int shelfLife;

    public Cake(String name, double price, double weight, int shelfLife) {
        super(name, price, weight);
        this.setCategory("Cake");
        this.shelfLife = shelfLife;
    }

    public Cake() {
        super();
    }

    @Override
    public int getShelfLife() {
        return shelfLife;
    }
}
