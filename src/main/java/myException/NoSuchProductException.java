package myException;

public class NoSuchProductException extends RuntimeException {
    public NoSuchProductException() {
        super("Не нашлось такого продукта!");
    }
}
