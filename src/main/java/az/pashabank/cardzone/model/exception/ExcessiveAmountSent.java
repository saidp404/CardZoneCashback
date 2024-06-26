package az.pashabank.cardzone.model.exception;

public class ExcessiveAmountSent extends RuntimeException {
    public ExcessiveAmountSent(String message) {
        super(message);
    }
}
