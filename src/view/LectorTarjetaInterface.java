package view;

import events.CardReadedListener;

/**
 * Métodos que debe implementar la interfaz visual de 'Inicio'
 *
 */

public interface LectorTarjetaInterface extends Visible {
	
	/**
	 * Registra al controlador como un listener de los eventos generados desde la view 'Inicio'.
	 *
	 */
	
	void setCardReadedListener(CardReadedListener listener);

	void editarTexto(String string);
}
