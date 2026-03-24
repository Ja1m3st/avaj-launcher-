package ro.academyplus.avaj.simulator.exceptions;

public class InvalidNameException extends Exception{

    public InvalidNameException(String message) {
        super(message);
    }

    public InvalidNameException(String message, Throwable err) {
        super(message, err);
    }

}
