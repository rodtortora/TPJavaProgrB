package events;

/**
 * Interface con m�todos que deben implementar los listener del evento 'tarjeta validada'.
 */

public interface CardValidatedListener {
	
	void listenCardValidatedEvent(CardValidatedEvent event);

}
