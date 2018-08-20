package exceptions;

public class WrongPinException extends Exception {

	public WrongPinException() {}

	public WrongPinException(String message) {
		super(message);
	}

}
