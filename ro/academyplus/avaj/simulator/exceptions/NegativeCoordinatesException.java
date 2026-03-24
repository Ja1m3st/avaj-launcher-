package ro.academyplus.avaj.simulator.exceptions;

public class NegativeCoordinatesException extends Exception {

    public NegativeCoordinatesException(String message) {
        super(message);
    }

    public NegativeCoordinatesException(String message, Throwable err) {
        super(message, err);
    }

}
