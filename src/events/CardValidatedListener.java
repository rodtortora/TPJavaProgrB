package events;

/**
 * Interface con métodos que deben implementar los listener del evento 'tarjeta validada'.
 */

public interface CardValidatedListener {
	
	void listenCardValidatedEvent(CardValidatedEvent event);

}
