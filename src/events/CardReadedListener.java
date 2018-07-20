package events;

/**
 * Interface con métodos que deben implementar los listener del evento 'tarjeta leída'.
 */

public interface CardReadedListener {
	

	/**
	 * Método que define el comportamiento producido por la lectura de una tarjeta.
	 * @param event es el evento que se le envía al listener, y con el cuál debe trabajar.
	 */
	
	void listenCardReadedEvent(CardReadedEvent event);

}
