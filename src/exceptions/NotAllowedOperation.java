package exceptions;

public class NotAllowedOperation extends Exception {
	
	public NotAllowedOperation() {}

	public NotAllowedOperation(String message) {
		super(message);
	}

}
