package exceptions;

public class ExtractionLimitExceeded extends Exception {

	public ExtractionLimitExceeded() {}

	public ExtractionLimitExceeded(String message) {
		super(message);
	}
}
