package exceptions;

public class ImpossibleCreateAccountException extends Exception {

	public ImpossibleCreateAccountException() {}
	
	public ImpossibleCreateAccountException(String message) {
		super(message);
	}

}
