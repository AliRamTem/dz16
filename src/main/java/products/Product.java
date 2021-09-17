package products;

import java.io.Serializable;
import java.util.Objects;

public abstract class Product implements Serializable {
    private String name;
    private double price;
    private double weight;
    private String category;

    public Product(String name, double price, double weight) {
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Товар{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", category='" + category + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return price == product.price && weight == product.weight && name.equals(product.name) && category.equals(product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category);
    }
}
