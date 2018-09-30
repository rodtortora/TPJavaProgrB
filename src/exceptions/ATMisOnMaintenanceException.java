package exceptions;

public class ATMisOnMaintenanceException extends Exception {

	public ATMisOnMaintenanceException() {}

	public ATMisOnMaintenanceException(String message) {
		super(message);
	}

}
