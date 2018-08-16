package events;

/**
 * Interface con métodos que deben implementar los listener del evento 'error en pin'.
 */

public interface PinFailedListener {
	
	
	/**
	 * Método que define el comportamiento producido por el evento de introducción de PIN erróneo.
	 * @param event es el evento que se le envía al listener, y con el cuál debe trabajar.
	 */
	
	void listenPinFailedEvent(PinFailedEvent event);

}
