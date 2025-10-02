package Bart.Exceptions;

public class CorruptStorageException extends RuntimeException {
    public CorruptStorageException(String message) {
        super(message);
    }
}
