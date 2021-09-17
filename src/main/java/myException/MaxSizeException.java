package myException;

public class MaxSizeException extends RuntimeException {
    public MaxSizeException() {
        super("В магазине нет места!");
    }
}
