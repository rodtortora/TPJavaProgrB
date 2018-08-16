package events;

/**
 * Interface con m�todos que deben implementar los listener del evento 'error en pin'.
 */

public interface PinFailedListener {
	
	
	/**
	 * M�todo que define el comportamiento producido por el evento de introducci�n de PIN err�neo.
	 * @param event es el evento que se le env�a al listener, y con el cu�l debe trabajar.
	 */
	
	void listenPinFailedEvent(PinFailedEvent event);

}
