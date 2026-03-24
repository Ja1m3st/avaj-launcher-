package ro.academyplus.avaj.simulator.exceptions;
public class InvalidScenarioException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    private final int lineNumber;

    public InvalidScenarioException(String message) {
        super(message);
        this.lineNumber = -1;
    }

    public InvalidScenarioException(String message, Throwable cause) {
        super(message, cause);
        this.lineNumber = -1;
    }

    public InvalidScenarioException(String message, int lineNumber) {
        super(message + " (in the line: " + lineNumber + ")");
        this.lineNumber = lineNumber;
    }

    public InvalidScenarioException(String message, Throwable cause, int lineNumber) {
        super(message + " (in the line: " + lineNumber + ")", cause);
        this.lineNumber = lineNumber;
    }

    public int getLineNumber() {
        return lineNumber;
    }
}
