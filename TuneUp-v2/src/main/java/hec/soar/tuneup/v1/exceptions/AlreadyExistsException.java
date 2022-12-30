package hec.soar.tuneup.v1.exceptions;

public class AlreadyExistsException extends Exception {

    private final String message;

    public AlreadyExistsException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}