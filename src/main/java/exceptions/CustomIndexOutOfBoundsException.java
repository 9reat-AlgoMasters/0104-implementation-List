package exceptions;

public class CustomIndexOutOfBoundsException extends Exception {
    public CustomIndexOutOfBoundsException() {
        this("올바르지 않은 인덱스");
    }
    
    public CustomIndexOutOfBoundsException(String message) {
        super(message);
    }
}
