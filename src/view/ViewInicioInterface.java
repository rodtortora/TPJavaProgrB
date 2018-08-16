package view;

import events.CardReadedListener;

/**
 * Métodos que debe implementar la interfaz visual de 'Inicio'
 *
 */

public interface ViewInicioInterface {
	
	/**
	 * Registra al controlador como un listener de los eventos generados desde la view 'Inicio'.
	 *
	 */
	
	void setCardReadedListener(CardReadedListener listener);
	void ocultar();
	void inicializar();

}
