package az.pashabank.cardzone.model.exception;

public class NoCardFoundByIdException extends RuntimeException{
    public NoCardFoundByIdException(String message) {
        super(message);
    }
}
