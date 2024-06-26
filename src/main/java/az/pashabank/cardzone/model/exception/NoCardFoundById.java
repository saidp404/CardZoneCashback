package az.pashabank.cardzone.model.exception;

public class NoCardFoundById extends RuntimeException{
    public NoCardFoundById(String message) {
        super(message);
    }
}
