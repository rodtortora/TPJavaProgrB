package events;

/**
 * Interface con m�todos que deben implementar los listener del evento 'tarjeta le�da'.
 */

public interface CardReadedListener {
	

	/**
	 * M�todo que define el comportamiento producido por la lectura de una tarjeta.
	 * @param event es el evento que se le env�a al listener, y con el cu�l debe trabajar.
	 */
	
	void listenCardReadedEvent(CardReadedEvent event);

}
