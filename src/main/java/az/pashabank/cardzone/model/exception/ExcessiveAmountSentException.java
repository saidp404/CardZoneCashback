package az.pashabank.cardzone.model.exception;

public class ExcessiveAmountSentException extends RuntimeException {
    public ExcessiveAmountSentException(String message) {
        super(message);
    }
}
