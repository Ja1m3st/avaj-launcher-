package ro.academyplus.avaj.simulator.exceptions;

public class InvalidTypeException extends Exception {

    public InvalidTypeException(String message) {
        super(message);
    }

    public InvalidTypeException(String message, Throwable err) {
        super(message, err);
    }

}
