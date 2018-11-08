package exceptions;

public class ImpossibleCreateCardException extends Exception {

	public ImpossibleCreateCardException() {}
	
	public ImpossibleCreateCardException(String message) {
		super(message);
	}

}
