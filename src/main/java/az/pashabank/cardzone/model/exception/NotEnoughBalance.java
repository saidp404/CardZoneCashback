package az.pashabank.cardzone.model.exception;

public class NotEnoughBalance extends RuntimeException {
    public NotEnoughBalance(String message) {
        super(message);
    }
}
